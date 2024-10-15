import React from "react";
import "../BankingWelcomePage.css";
import welcomeimg from "../../image/welcomepage mage.jpg";
import twitter from "../../image/twitter.512x416.png";
import welcomwallet from "../../image/welcomwallet.jpg";
import welcomeShopping from "../../image/welcomeShopping.jpg";
import welcomecredit from "../../image/welcomecredit.jpg";
import about from "../../image/about.jpg";
import blog1 from "../../image/blog1.jpg";
import blog2 from "../../image/blog2.jpg";
import blog3 from "../../image/blog3.jpg";
import phone from "../../image/phone.png";
import instagram from "../../image/instagram.png";
import location from "../../image/location.png";
import mail from "../../image/mail.png";

import { useNavigate, Link } from "react-router-dom";
import { useEffect, useRef } from "react";

function WelcomePage() {
  const navigate = useNavigate();
  const scrollSections = useRef([]);
  const homeRef = useRef(null);
  const aboutRef = useRef(null);
  const blogRef = useRef(null);
  const contactRef = useRef(null);

  const scrollToSection = (elementRef) => {
    if (elementRef && elementRef.current) {
      elementRef.current.scrollIntoView({ behavior: "smooth" });
    }
  };
  useEffect(() => {
    const observerOptions = {
      root: null,
      rootMargin: "5px",
      threshold: 0.3,
    };

    const observerCallback = (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("active");
        } else {
          entry.target.classList.remove("active");
        }
      });
    };

    const observer = new IntersectionObserver(
      observerCallback,
      observerOptions
    );

    scrollSections.current.forEach((section) => {
      if (section) {
        observer.observe(section);
      }
    });

    return () => {
      scrollSections.current.forEach((section) => {
        if (section) {
          observer.unobserve(section);
        }
      });
    };
  }, []);

  // useEffect(() => {
  //   const aboutUsSection = document.querySelector(".welcome-aboutUs");
  //   aboutUsSection.classList.add("active");
  // }, []);
  // const navigate = useNavigate();
  return (
    <div>
      <div className="page" ref={(el) => (scrollSections.current[0] = el)}>
        <div className="Welcomeimage">
          <img src={welcomeimg} alt="welcome-image" className="Welcomeimage" />
        </div>
        <div className="container">
          <navbar className="navbar">
            <div className="logo">
              <div className="nav-item">
                <button className="logo-btn">Easy Banking</button>
              </div>
            </div>
            <div className="main-btn">
              <div className="nav-item">
                <div className="home">
                  <button
                    className="home-btn"
                    onClick={() => scrollToSection(homeRef)}
                  >
                    Home
                  </button>
                </div>
              </div>
              <div className="nav-item">
                <div className="aboutus">
                  <button
                    className="aboutus-btn"
                    onClick={() => scrollToSection(aboutRef)}
                  >
                    About us
                  </button>
                </div>
              </div>
              <div className="nav-item">
                <div className="blog">
                  <button
                    className="blog-btn"
                    onClick={() => scrollToSection(blogRef)}
                  >
                    Blog
                  </button>
                </div>
              </div>
              <div className="nav-item">
                <div className="contact">
                  <button
                    className="contact-btn"
                    onClick={() => scrollToSection(contactRef)}
                  >
                    Contact
                  </button>
                </div>
              </div>
            </div>

            <div className="socialmedia">
              <div className="nav-item">
                <div className="facebook">
                  <button className="facebook-btn">f</button>
                </div>
              </div>
              <div className="nav-item">
                <div className="x">
                  <button className="x-btn">
                    <img src={twitter} alt="x" className="x-img" />
                  </button>
                </div>
              </div>
              <div className="nav-item">
                <div className="instagram">
                  <button className="instagram-btn">in</button>
                </div>
              </div>
            </div>
          </navbar>
          <div className="hr-line"></div>
          <div className="page-body">
            <div>
              <div className="name">
                <p className="name-para"></p>
              </div>
              <div className="details">
                <p className="details-para">
                  <p className="solution">EASY ONLINE BANKING</p>
                  <p className="description">
                    Hit Register to Make Life Easier with Digital Banking!
                  </p>
                </p>
              </div>
              <div className="register-login">
                <span className="login">
                  <button
                    className="login-btn"
                    onClick={() => navigate("/login")}
                  >
                    Login
                  </button>
                </span>
                <span className="register">
                  <button
                    className="register-btn"
                    onClick={() => navigate("/register")}
                  >
                    Register
                  </button>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        className="welcome-aboutUs scroll-section"
        ref={(el) => (scrollSections.current[1] = el)}
      >
        <div className="welcome-aboutus-container">
          <h2>About Us</h2>
          <p>
            Easy Banking is revolutionizing the way you manage your finances.
            With our state-of-the-art digital platform, we bring the power of
            banking right to your fingertips. Our mission is to provide
            seamless, secure, and smart banking solutions that adapt to your
            lifestyle.
          </p>
        </div>
        <div className="welcome-problem-solving active">
          <img
            alt="problem-solving"
            className="scroll-welcome-image"
            src={about}
          />
          <h3>We Solve Your Financial Problems</h3>
          <p>
            At Easy Banking, we understand the complexities of modern finance.
            Our team of experts is dedicated to developing innovative solutions
            that simplify your banking experience and help you achieve your
            financial goals.
          </p>
        </div>
      </div>

      <div
        ref={(el) => (scrollSections.current[2] = el)}
        className="image-with-details scroll-section"
      >
        <div className="feature-card">
          <img
            alt="money-management"
            className="feature-img"
            src={welcomwallet}
          />
          <h3>Smart Money Management</h3>
          <p>
            Take control of your finances with our intuitive tools and insights,
            helping you make informed decisions about your money.
          </p>
        </div>
        <div className="feature-card">
          <img
            alt="secure-transactions"
            className="feature-img"
            src={welcomeShopping}
          />
          <h3>Secure Transactions</h3>
          <p>
            Rest easy knowing your transactions are protected by
            state-of-the-art security measures, keeping your money safe at all
            times.
          </p>
        </div>
        <div className="feature-card">
          <img
            alt="digital-cards"
            className="feature-img"
            src={welcomecredit}
          />
          <h3>Digital Cards</h3>
          <p>
            Experience the convenience of our digital cards, offering seamless
            integration with your favorite payment platforms.
          </p>
        </div>
      </div>

      <div
        ref={(el) => (scrollSections.current[3] = el)}
        className="blog-container scroll-section"
      >
        <h2>Latest from Our Blog</h2>
        <div className="blog-grid">
          <div className="blog-card">
            <img alt="blog-1" className="blog-image" src={blog1} />
            <h3>The Future of Digital Banking</h3>
            <p>
              Explore the latest trends shaping the future of online banking and
              how they benefit you.
            </p>
            <Link className="read-more">Read More</Link>
          </div>
          <div className="blog-card">
            <img alt="blog-2" className="blog-image" src={blog2} />
            <h3>Mastering Personal Finance</h3>
            <p>
              Learn essential tips and strategies to take control of your
              personal finances and achieve your goals.
            </p>
            <Link className="read-more">Read More</Link>
          </div>
          <div className="blog-card">
            <img alt="blog-3" className="blog-image" src={blog3} />
            <h3>Cybersecurity in Banking</h3>
            <p>
              Discover how we're staying ahead of the curve to keep your
              financial information secure.
            </p>
            <Link className="read-more">Read More</Link>
          </div>
        </div>
      </div>

      <div
        ref={(el) => (scrollSections.current[4] = el)}
        className="contactUs-container scroll-section"
      >
        <h2>Contact Us</h2>
        <div className="contact-info">
          <div className="contact-item">
            <img className="location" src={location} alt="location"></img>
            <p>123 Banking Street, Bangalore City, Karnataka</p>
          </div>
          <div className="contact-item">
            <img className="phone" src={phone} alt="phone "></img>
            <p>+91 99887 99887</p>
          </div>
          <div className="contact-item">
            <img className="email" src={mail} alt="email"></img>
            <p>easybanking.com</p>
          </div>
        </div>
      </div>

      <footer className="welcome-footer-container">
        <div className="footer-content">
          <div className="footer-section">
            <h3>About Us</h3>
            <p>
              Easy Banking: Your trusted partner for innovative and secure
              online banking solutions.
            </p>
          </div>
          <div className="footer-section">
            <h3>Quick Links</h3>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/about">About Us</Link>
              </li>
              <li>
                <Link to="/services">Services</Link>
              </li>
              <li>
                <Link to="/blog">Blog</Link>
              </li>
              <li>
                <Link to="/contact">Contact</Link>
              </li>
            </ul>
          </div>
          <div className="footer-section">
            <h3>Follow Us</h3>
            <div className="footer-social-media">
              <button className="social-icon">f</button>
              <button className="social-icon">
                <img className="twitter" src={twitter} alt="twitter"></img>
              </button>
              <button className="social-icon">
                <img
                  className="instagram"
                  src={instagram}
                  alt="instagram"
                ></img>
              </button>
              <button className="social-icon">in</button>
            </div>
          </div>
        </div>
        <div className="footer-bottom">
          <p>&copy; 2024 Easy Banking. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
}

export default WelcomePage;
