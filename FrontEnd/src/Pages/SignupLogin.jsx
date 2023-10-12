import { useState } from "react";
import "./SignupLogin.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";

function SignupLogin() {
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
    });
    const [isSignup, setSignup] = useState(true);
    const [isPasswordVisible, setPasswordVisibility] = useState(false);
    const [isConfirmPasswordVisible, setConfirmPasswordVisibility] =
        useState(false);

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    const showSignup = () => {
        setSignup(true);
    };
    const showLogin = () => {
        setSignup(false);
    };
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (isSignup) {
            if (formData.password !== formData.confirmPassword) {
                alert("Passwords do not match");
                return;
            }

            try {
                const signupRes = await axios.post(
                    "http://localhost:8080/Users",
                    {
                        name: formData.name,
                        email: formData.email,
                        password: formData.password,
                    }
                );
                console.log(signupRes.status);
                if (signupRes.status == 201) {
                    alert("User signed up successfully");
                    setFormData({
                        name: "",
                        email: "",
                        password: "",
                        confirmPassword: "",
                    });
                    showLogin();
                }
            } catch (signupError) {
                console.error("Failed to signup:", signupError);
                alert("Signup failed");
            }
        } else {
            try {
                const loginRes = await axios.post(
                    "http://localhost:8080/login",
                    {
                        email: formData.email,
                        password: formData.password,
                    }
                );
                console.log(loginRes);
                if (loginRes.status == 200) {
                    alert("Login successful");
                    localStorage.setItem("email", loginRes.data.email);
                    localStorage.setItem("id", loginRes.data.uid);
                    localStorage.setItem("name", loginRes.data.name);

                    navigate("/task");
                }
            } catch (loginError) {
                console.error("Failed to log in:", loginError);
                alert("Invalid Credentials");
            }
        }
    };

    return (
        <>
            <div id="signup-login-main">
                <div id="toggle-button">
                    <span
                        onClick={showSignup}
                        className={isSignup ? "toggle-true" : "toggle-false"}
                    >
                        Signup
                    </span>
                    <span
                        onClick={showLogin}
                        className={!isSignup ? "toggle-true" : "toggle-false"}
                    >
                        Login
                    </span>
                </div>
                <div id="form-container">
                    <form onSubmit={handleSubmit}>
                        {isSignup ? <div>
                            <input
                                type="text"
                                name="name"
                                value={formData.name}
                                placeholder="Name"
                                onChange={handleChange}
                                required
                            />
                        </div> : null}
                        <div>
                            <input
                                type="email"
                                name="email"
                                value={formData.email}
                                placeholder="E-mail"
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div>
                            <input
                                type={isPasswordVisible ? "text" : "password"}
                                name="password"
                                value={formData.password}
                                placeholder="Password"
                                onChange={handleChange}
                                required
                            />
                            <span
                                className="icon"
                                onClick={() => setPasswordVisibility(!isPasswordVisible)}
                            >
                                {isPasswordVisible ? (
                                    <FontAwesomeIcon icon={faEye} />
                                ) : (
                                    <FontAwesomeIcon icon={faEyeSlash} />
                                )}
                            </span>
                        </div>

                        {isSignup ? (
                            <div>
                                <input
                                    type={isConfirmPasswordVisible ? "text" : "password"}
                                    name="confirmPassword"
                                    value={formData.confirmPassword}
                                    placeholder="Confirm Password"
                                    onChange={handleChange}
                                    required
                                />
                                <span
                                    className="icon"
                                    onClick={() =>
                                        setConfirmPasswordVisibility(!isConfirmPasswordVisible)
                                    }
                                >
                                    {isConfirmPasswordVisible ? (
                                        <FontAwesomeIcon icon={faEye} />
                                    ) : (
                                        <FontAwesomeIcon icon={faEyeSlash} />
                                    )}
                                </span>
                            </div>
                        ) : null}

                        <div>
                            <button type="submit">{isSignup ? "Signup" : "Login"}</button>
                        </div>
                    </form>
                    <p>
                        {isSignup ? "Already have an account?" : "Dont have an account?"}
                    </p>

                    <span id="change-handler">
                        {isSignup ? (
                            <span onClick={showLogin}>Login here</span>
                        ) : (
                            <span onClick={showSignup}>Signup here</span>
                        )}
                    </span>
                </div>
            </div>
        </>
    );
}

export default SignupLogin;