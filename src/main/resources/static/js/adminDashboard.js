let adminChartInstance = null;
let adminDistributionChartInstance = null;
let currentDistributionType = "doughnut";
let lastFetchedStats = null;
let allEventsCache = [];
let allCharitiesCache = [];

function closeAdminModal(id) {
    document.getElementById(id).style.display = "none";
}

function openAdminCreateModal() {
    const select = document.getElementById("admin-create-charity");
    if (allCharitiesCache.length === 0) {
        select.innerHTML = `<option value="">No registered charities found</option>`;
    } else {
        select.innerHTML = allCharitiesCache.map(c => `
            <option value="${c.userId}">#${c.userId} - ${c.orgName} (${c.regNumber})</option>
        `).join("");
    }
    document.getElementById("admin-create-modal").style.display = "flex";
}

async function loadAdminDashboard() {
    const user = getCurrentUser();
    if (!user) {
        window.location.href = "login.html";
        return;
    }
    if (user.role !== "ADMIN") {
        redirectUserToRoleDashboard(user.role);
        return;
    }

    await Promise.all([
        loadAdminStats(),
        loadAdminEvents(),
        loadAdminCharities()
    ]);
}

async function loadAdminStats() {
    try {
        const stats = await apiFetch("/admin/stats");
        lastFetchedStats = stats;

        document.getElementById("stat-events").innerText = stats.totalEvents || 0;
        document.getElementById("stat-funds").innerText = `$${(stats.totalFundsRaised || 0).toLocaleString()}`;
        document.getElementById("stat-vols").innerText = stats.totalVolunteers || 0;
        document.getElementById("stat-charities").innerText = stats.totalCharities || 0;
        document.getElementById("stat-donors").innerText = stats.totalDonors || 0;
        document.getElementById("stat-hours").innerText = `${stats.totalHoursLogged || 0}h`;

        renderAdminBarChart(stats);
        renderAdminDistributionChart(stats);
    } catch (err) {
        showAdminAlert(err.message);
    }
}

/**
 * 1. Render Dynamic Bar Chart (Global Metric Overview)
 */
function renderAdminBarChart(stats) {
    const canvas = document.getElementById("adminStatsChart");
    if (!canvas) return;
    const ctx = canvas.getContext("2d");

    if (adminChartInstance) {
        adminChartInstance.destroy();
    }

    // Prepare values (if all 0, provide baseline demo data for dynamic visual preview)
    const events = stats.totalEvents || 3;
    const vols = stats.totalVolunteers || 15;
    const charities = stats.totalCharities || 4;
    const donors = stats.totalDonors || 8;
    const hours = stats.totalHoursLogged || 48;

    adminChartInstance = new Chart(ctx, {
        type: "bar",
        data: {
            labels: ["Events", "Volunteers", "Charities", "Donors", "Hours Logged"],
            datasets: [{
                label: "Platform Metrics",
                data: [events, vols, charities, donors, hours],
                backgroundColor: [
                    "rgba(37, 99, 235, 0.85)",   // Primary Blue
                    "rgba(16, 185, 129, 0.85)",  // Secondary Green
                    "rgba(245, 158, 11, 0.85)",  // Warning Orange
                    "rgba(139, 92, 246, 0.85)",  // Purple
                    "rgba(236, 72, 153, 0.85)"   // Pink
                ],
                borderColor: ["#1d4ed8", "#059669", "#d97706", "#7c3aed", "#db2777"],
                borderWidth: 2,
                borderRadius: 8,
                borderSkipped: false
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            animation: {
                duration: 1200,
                easing: "easeOutQuart"
            },
            plugins: {
                legend: { display: false },
                tooltip: {
                    backgroundColor: "#0f172a",
                    titleFont: { size: 14, weight: "bold" },
                    bodyFont: { size: 13 },
                    padding: 12,
                    displayColors: false,
                    callbacks: {
                        label: function(context) {
                            return `${context.label}: ${context.raw.toLocaleString()}`;
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: { color: "#f1f5f9" },
                    ticks: { font: { family: "Inter", size: 12 } }
                },
                x: {
                    grid: { display: false },
                    ticks: { font: { family: "Inter", size: 12, weight: "600" } }
                }
            }
        }
    });
}

/**
 * 2. Render Dynamic Doughnut / Pie Chart (Platform Distribution)
 */
function renderAdminDistributionChart(stats) {
    const canvas = document.getElementById("adminDistributionChart");
    if (!canvas) return;
    const ctx = canvas.getContext("2d");

    if (adminDistributionChartInstance) {
        adminDistributionChartInstance.destroy();
    }

    const vols = stats ? (stats.totalVolunteers || 15) : 15;
    const charities = stats ? (stats.totalCharities || 4) : 4;
    const donors = stats ? (stats.totalDonors || 8) : 8;

    // Count event statuses from cache if available
    let upcomingCount = allEventsCache.filter(e => e.status === 'UPCOMING').length || 2;
    let ongoingCount = allEventsCache.filter(e => e.status === 'ONGOING').length || 1;

    adminDistributionChartInstance = new Chart(ctx, {
        type: currentDistributionType,
        data: {
            labels: ["Volunteers", "Charity NGOs", "Donors", "Upcoming Events", "Ongoing Events"],
            datasets: [{
                data: [vols, charities, donors, upcomingCount, ongoingCount],
                backgroundColor: [
                    "#2563eb", // Blue
                    "#10b981", // Green
                    "#8b5cf6", // Purple
                    "#f59e0b", // Amber
                    "#06b6d4"  // Cyan
                ],
                borderWidth: 3,
                borderColor: "#ffffff",
                hoverOffset: 12
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            cutout: currentDistributionType === "doughnut" ? "65%" : "0%",
            animation: {
                animateRotate: true,
                animateScale: true,
                duration: 1000
            },
            plugins: {
                legend: {
                    position: "bottom",
                    labels: {
                        font: { family: "Inter", size: 11, weight: "600" },
                        padding: 16,
                        usePointStyle: true
                    }
                },
                tooltip: {
                    backgroundColor: "#0f172a",
                    padding: 12,
                    callbacks: {
                        label: function(context) {
                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                            const val = context.raw;
                            const pct = Math.round((val / total) * 100);
                            return `${context.label}: ${val} (${pct}%)`;
                        }
                    }
                }
            }
        }
    });
}

/**
 * Toggle between Doughnut & Pie Chart Views dynamically
 */
function toggleDistributionChartType() {
    currentDistributionType = currentDistributionType === "doughnut" ? "pie" : "doughnut";
    if (lastFetchedStats) {
        renderAdminDistributionChart(lastFetchedStats);
    }
}

async function loadAdminEvents() {
    const tbody = document.getElementById("admin-events-tbody");
    try {
        allEventsCache = await apiFetch("/admin/events");
        if (lastFetchedStats) {
            renderAdminDistributionChart(lastFetchedStats);
        }

        if (allEventsCache.length === 0) {
            tbody.innerHTML = `<tr><td colspan="8" style="padding: 1rem; text-align: center; color: var(--text-secondary);">No events found on the platform.</td></tr>`;
            return;
        }

        tbody.innerHTML = allEventsCache.map(e => `
            <tr style="border-bottom: 1px solid var(--border);">
                <td style="padding: 0.75rem;">#${e.id}</td>
                <td style="padding: 0.75rem; font-weight: 600;">${e.title}</td>
                <td style="padding: 0.75rem;">Charity #${e.charityId}</td>
                <td style="padding: 0.75rem;">
                    <select onchange="updateEventStatus(${e.id}, this.value)" style="padding: 0.2rem 0.4rem; border-radius: 4px; border: 1px solid var(--border); font-size: 0.8rem; background: var(--card-bg);">
                        <option value="UPCOMING" ${e.status === 'UPCOMING' ? 'selected' : ''}>UPCOMING</option>
                        <option value="ONGOING" ${e.status === 'ONGOING' ? 'selected' : ''}>ONGOING</option>
                        <option value="COMPLETED" ${e.status === 'COMPLETED' ? 'selected' : ''}>COMPLETED</option>
                    </select>
                </td>
                <td style="padding: 0.75rem; color: var(--secondary); font-weight: 600;">$${e.collectedAmount || 0} / $${e.targetAmount || 0}</td>
                <td style="padding: 0.75rem;">${e.volunteersAccepted || 0} / ${e.volunteersNeeded || 0}</td>
                <td style="padding: 0.75rem;">${e.eventDate}</td>
                <td style="padding: 0.75rem; text-align: center;">
                    <button class="btn btn-outline" style="padding: 0.25rem 0.5rem; font-size: 0.75rem; margin-right: 0.25rem;" onclick="openEditEventModal(${e.id})">Edit</button>
                    <button class="btn btn-danger" style="padding: 0.25rem 0.5rem; font-size: 0.75rem;" onclick="deleteEvent(${e.id})">Delete</button>
                </td>
            </tr>
        `).join("");
    } catch (err) {
        tbody.innerHTML = `<tr><td colspan="8" style="color: var(--danger); padding: 1rem;">${err.message}</td></tr>`;
    }
}

async function loadAdminCharities() {
    const tbody = document.getElementById("admin-charities-tbody");
    try {
        allCharitiesCache = await apiFetch("/admin/charities");
        if (allCharitiesCache.length === 0) {
            tbody.innerHTML = `<tr><td colspan="5" style="padding: 1rem; text-align: center; color: var(--text-secondary);">No charity profiles registered.</td></tr>`;
            return;
        }

        tbody.innerHTML = allCharitiesCache.map(c => `
            <tr style="border-bottom: 1px solid var(--border);">
                <td style="padding: 0.75rem;">User #${c.userId}</td>
                <td style="padding: 0.75rem; font-weight: 600;">${c.orgName}</td>
                <td style="padding: 0.75rem;">${c.regNumber}</td>
                <td style="padding: 0.75rem;">
                    <span class="badge ${c.verifiedStatus ? 'badge-success' : 'badge-warning'}">
                        ${c.verifiedStatus ? 'VERIFIED' : 'PENDING'}
                    </span>
                </td>
                <td style="padding: 0.75rem; text-align: center;">
                    ${c.verifiedStatus ? `
                        <button class="btn btn-outline" style="padding: 0.25rem 0.5rem; font-size: 0.75rem;" onclick="toggleCharityVerification(${c.userId}, false)">Unverify</button>
                    ` : `
                        <button class="btn btn-primary" style="padding: 0.25rem 0.5rem; font-size: 0.75rem;" onclick="toggleCharityVerification(${c.userId}, true)">Verify NGO</button>
                    `}
                </td>
            </tr>
        `).join("");
    } catch (err) {
        tbody.innerHTML = `<tr><td colspan="5" style="color: var(--danger); padding: 1rem;">${err.message}</td></tr>`;
    }
}

async function toggleCharityVerification(userId, verify) {
    try {
        const endpoint = verify ? `/admin/charities/${userId}/verify` : `/admin/charities/${userId}/unverify`;
        await apiFetch(endpoint, { method: "PUT" });
        loadAdminCharities();
    } catch (err) {
        showAdminAlert(err.message);
    }
}

async function updateEventStatus(eventId, newStatus) {
    try {
        await apiFetch(`/admin/events/${eventId}/status?status=${newStatus}`, { method: "PUT" });
        loadAdminEvents();
    } catch (err) {
        showAdminAlert(err.message);
    }
}

async function deleteEvent(eventId) {
    if (!confirm(`Are you sure you want to delete Event #${eventId}? This action cannot be undone.`)) return;
    try {
        await apiFetch(`/admin/events/${eventId}`, { method: "DELETE" });
        loadAdminEvents();
        loadAdminStats();
    } catch (err) {
        showAdminAlert(err.message);
    }
}

function openEditEventModal(eventId) {
    const evt = allEventsCache.find(e => e.id === eventId);
    if (!evt) return;

    document.getElementById("admin-edit-id").value = evt.id;
    document.getElementById("admin-edit-title").value = evt.title;
    document.getElementById("admin-edit-type").value = evt.type || "General";
    document.getElementById("admin-edit-date").value = evt.eventDate;
    document.getElementById("admin-edit-location").value = evt.location;
    document.getElementById("admin-edit-volunteers").value = evt.volunteersNeeded;
    document.getElementById("admin-edit-target").value = evt.targetAmount;
    document.getElementById("admin-edit-desc").value = evt.description;

    document.getElementById("admin-edit-modal").style.display = "flex";
}

document.getElementById("admin-create-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const charityId = document.getElementById("admin-create-charity").value;
    if (!charityId) {
        showAdminAlert("Please select a valid charity.");
        return;
    }

    const payload = {
        title: document.getElementById("admin-create-title").value,
        type: document.getElementById("admin-create-type").value,
        eventDate: document.getElementById("admin-create-date").value,
        location: document.getElementById("admin-create-location").value,
        volunteersNeeded: +document.getElementById("admin-create-volunteers").value,
        targetAmount: +document.getElementById("admin-create-target").value,
        description: document.getElementById("admin-create-desc").value,
        status: "UPCOMING"
    };

    try {
        await apiFetch(`/admin/events?charityId=${charityId}`, {
            method: "POST",
            body: JSON.stringify(payload)
        });
        closeAdminModal("admin-create-modal");
        loadAdminEvents();
        loadAdminStats();
    } catch (err) {
        showAdminAlert(err.message);
    }
});

document.getElementById("admin-edit-form").addEventListener("submit", async (e) => {
    e.preventDefault();
    const eventId = document.getElementById("admin-edit-id").value;

    const payload = {
        title: document.getElementById("admin-edit-title").value,
        type: document.getElementById("admin-edit-type").value,
        eventDate: document.getElementById("admin-edit-date").value,
        location: document.getElementById("admin-edit-location").value,
        volunteersNeeded: +document.getElementById("admin-edit-volunteers").value,
        targetAmount: +document.getElementById("admin-edit-target").value,
        description: document.getElementById("admin-edit-desc").value
    };

    try {
        await apiFetch(`/admin/events/${eventId}`, {
            method: "PUT",
            body: JSON.stringify(payload)
        });
        closeAdminModal("admin-edit-modal");
        loadAdminEvents();
        loadAdminStats();
    } catch (err) {
        showAdminAlert(err.message);
    }
});

function showAdminAlert(msg) {
    const alertBox = document.getElementById("admin-alert");
    alertBox.innerText = msg;
    alertBox.style.display = "block";
    setTimeout(() => alertBox.style.display = "none", 4000);
}

document.addEventListener("DOMContentLoaded", loadAdminDashboard);
