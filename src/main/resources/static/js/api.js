/**
 * Shared API Fetch Wrapper for VolunTrack
 * - Attaches JWT Authorization header automatically from localStorage
 * - Uses relative path '/api' to avoid CORS issues when served from Spring Boot
 * - Provides clean, human-readable error messages
 */

const API_BASE = "/api";

async function apiFetch(endpoint, options = {}) {
    const token = localStorage.getItem("token");
    
    const headers = {
        "Content-Type": "application/json",
        ...options.headers
    };

    if (token) {
        headers["Authorization"] = `Bearer ${token}`;
    }

    const config = {
        ...options,
        headers
    };

    let response;
    try {
        response = await fetch(`${API_BASE}${endpoint}`, config);
    } catch (networkErr) {
        throw new Error("Unable to connect to VolunTrack backend server. Please verify the Spring Boot backend is running at http://localhost:8080");
    }

    let data;
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
        data = await response.json();
    } else {
        data = await response.text();
    }

    if (!response.ok) {
        const errorMsg = typeof data === "object" && data.message ? data.message : (typeof data === "string" ? data : `HTTP ${response.status} Error`);
        throw new Error(errorMsg);
    }

    return data;
}

// User Session & Role Helpers
function getCurrentUser() {
    const userStr = localStorage.getItem("user");
    return userStr ? JSON.parse(userStr) : null;
}

function getRoleDashboardUrl(role) {
    if (!role) return "login.html";
    const r = role.toUpperCase();
    if (r === "ADMIN") return "admin-dashboard.html";
    if (r === "CHARITY") return "charity-dashboard.html";
    if (r === "VOLUNTEER") return "volunteer-dashboard.html";
    if (r === "DONOR") return "donor-dashboard.html";
    return "events.html";
}

function redirectUserToRoleDashboard(role) {
    window.location.href = getRoleDashboardUrl(role);
}

function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

function updateNavAuth() {
    const user = getCurrentUser();
    const authNav = document.getElementById("nav-auth-container");
    if (!authNav) return;

    if (user) {
        const dashUrl = getRoleDashboardUrl(user.role);
        authNav.innerHTML = `
            <span style="font-size: 0.9rem; margin-right: 1rem; color: #475569;">Logged in as: <strong>${user.name}</strong> (${user.role})</span>
            <a href="${dashUrl}" class="btn btn-outline" style="padding: 0.375rem 0.75rem; font-size: 0.85rem; margin-right: 0.5rem;">Dashboard</a>
            <button onclick="logout()" class="btn btn-danger" style="padding: 0.375rem 0.75rem; font-size: 0.85rem;">Logout</button>
        `;
    } else {
        authNav.innerHTML = `
            <a href="login.html" class="btn btn-outline" style="padding: 0.375rem 0.75rem; font-size: 0.85rem; margin-right: 0.5rem;">Log In</a>
            <a href="register.html" class="btn btn-primary" style="padding: 0.375rem 0.75rem; font-size: 0.85rem;">Register</a>
        `;
    }
}

document.addEventListener("DOMContentLoaded", updateNavAuth);
