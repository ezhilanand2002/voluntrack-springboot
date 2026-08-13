/**
 * VolunTrack Landing Page Dynamic Interactivity & Animations
 */

document.addEventListener('DOMContentLoaded', () => {
  initNavbarScroll();
  initScrollReveals();
  initLiveTicker();
  initTestimonialsCarousel();
});

/**
 * 1. Sticky Navbar Transparency & Scroll Effect
 */
function initNavbarScroll() {
  const navbar = document.querySelector('.navbar');
  if (!navbar) return;

  // Set initial state
  if (window.scrollY < 40) {
    navbar.classList.add('transparent');
    navbar.classList.remove('scrolled');
  } else {
    navbar.classList.remove('transparent');
    navbar.classList.add('scrolled');
  }

  window.addEventListener('scroll', () => {
    if (window.scrollY < 40) {
      navbar.classList.add('transparent');
      navbar.classList.remove('scrolled');
    } else {
      navbar.classList.remove('transparent');
      navbar.classList.add('scrolled');
    }
  }, { passive: true });
}

/**
 * 2. Scroll-Triggered Fade & Slide-In Animations
 */
function initScrollReveals() {
  const revealElements = document.querySelectorAll('.reveal-on-scroll');
  if (!revealElements.length) return;

  const observerOptions = {
    root: null,
    rootMargin: '0px 0px -50px 0px',
    threshold: 0.15
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
 * 3. Live System Ticker Counter & Timestamp
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
  setInterval(updateTicker, 10000);
}

/**
 * 4. Testimonials Carousel Navigation
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
    dot.addEventListener('click', () => showSlide(idx));
    dotsContainer.appendChild(dot);
  });

  const dots = dotsContainer.querySelectorAll('.carousel-dot');

  function showSlide(index) {
    cards.forEach(c => c.classList.remove('active'));
    dots.forEach(d => d.classList.remove('active'));

    currentIndex = index;
    cards[currentIndex].classList.add('active');
    dots[currentIndex].classList.add('active');
  }

  // Auto-play testimonial slides
  setInterval(() => {
    let nextIndex = (currentIndex + 1) % cards.length;
    showSlide(nextIndex);
  }, 6000);
}
