/**
 * VolunTrack Main Shared JavaScript
 * Light/Dark Theme System with localStorage persistence,
 * Dynamic navbar transparency, scroll-reveal animations, ticker, and carousels
 */

// Theme Management Functions
function initTheme() {
  const savedTheme = localStorage.getItem('theme') || 'dark';
  document.documentElement.setAttribute('data-theme', savedTheme);
  updateThemeToggleIcon(savedTheme);
}

function toggleTheme() {
  const currentTheme = document.documentElement.getAttribute('data-theme') || 'dark';
  const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
  document.documentElement.setAttribute('data-theme', newTheme);
  localStorage.setItem('theme', newTheme);
  updateThemeToggleIcon(newTheme);
}

function updateThemeToggleIcon(theme) {
  const iconSpan = document.getElementById('theme-toggle-icon');
  if (iconSpan) {
    iconSpan.innerText = theme === 'light' ? '☀️' : '🌙';
  }
}

// Immediately initialize theme on script evaluation to prevent UI flash
initTheme();

document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  initNavbarScroll();
  initScrollReveals();
  initLiveTicker();
  initTestimonialsCarousel();
});

/**
 * 1. Sticky Navbar Transparency & Elevation Toggle
 */
function initNavbarScroll() {
  const navbar = document.querySelector('.navbar');
  if (!navbar) return;

  function checkScroll() {
    if (window.scrollY < 40) {
      navbar.classList.add('transparent');
      navbar.classList.remove('scrolled');
    } else {
      navbar.classList.remove('transparent');
      navbar.classList.add('scrolled');
    }
  }

  checkScroll();
  window.addEventListener('scroll', checkScroll, { passive: true });
}

/**
 * 2. IntersectionObserver Scroll Reveals
 */
function initScrollReveals() {
  const revealElements = document.querySelectorAll('.reveal-on-scroll');
  if (!revealElements.length) return;

  const observerOptions = {
    root: null,
    rootMargin: '0px 0px -40px 0px',
    threshold: 0.1
  };

  const observer = new IntersectionObserver((entries, obs) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        entry.target.classList.add('is-visible');
        obs.unobserve(entry.target);
      }
    });
  }, observerOptions);

  revealElements.forEach(el => observer.observe(el));
}

/**
 * 3. Live System Ticker Counter
 */
function initLiveTicker() {
  const tickerElement = document.getElementById('live-system-ticker');
  if (!tickerElement) return;

  function updateTicker() {
    const now = new Date();
    const isoDate = now.toISOString().slice(0, 10).replace(/-/g, '.');
    const randomCount = Math.floor(45890 + (now.getSeconds() * 3.7));
    tickerElement.textContent = `01/${randomCount} // Live System • ${isoDate}`;
  }

  updateTicker();
  setInterval(updateTicker, 8000);
}

/**
 * 4. Testimonials Swipeable / Auto-looping Carousel
 */
function initTestimonialsCarousel() {
  const cards = document.querySelectorAll('.testimonial-card');
  const dotsContainer = document.getElementById('carousel-dots');
  if (!cards.length || !dotsContainer) return;

  let currentIndex = 0;
  dotsContainer.innerHTML = '';

  cards.forEach((_, idx) => {
    const dot = document.createElement('span');
    dot.className = `carousel-dot ${idx === 0 ? 'active' : ''}`;
    dot.dataset.index = idx;
    dot.addEventListener('click', (e) => {
      const targetIdx = parseInt(e.currentTarget.dataset.index, 10);
      showSlide(targetIdx);
    });
    dotsContainer.appendChild(dot);
  });

  const dots = dotsContainer.querySelectorAll('.carousel-dot');

  function showSlide(index) {
    cards.forEach(c => c.classList.remove('active'));
    dots.forEach(d => d.classList.remove('active'));

    currentIndex = index;
    if (cards[currentIndex]) cards[currentIndex].classList.add('active');
    if (dots[currentIndex]) dots[currentIndex].classList.add('active');
  }

  setInterval(() => {
    let nextIndex = (currentIndex + 1) % cards.length;
    showSlide(nextIndex);
  }, 6000);
}
