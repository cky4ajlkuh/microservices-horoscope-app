import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigator = useNavigate();
    const showError = (message) => {
        toast.error(message);
    };
    const showSuccess = (message) => {
        toast.success(message);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const user = { username, password };

        axios.post('api/authentication/login', user, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            console.log(response.data);
            if (response.data.token) {
                showSuccess("Вы успешно вошли в систему!");
                let token = response.data.token;
                localStorage.setItem('name', token.name);
                localStorage.setItem('birthday', token.birthday);
                localStorage.setItem('zodiac', token.zodiac);
                navigator('/main');
            } else if (response.data.error) {
                showError(response.data.error);
            }
        })
        .catch(error => {
            showError(error);
        });
    };

  return (
        <div className="container gx-5 mt-4">
            <ToastContainer />
            <div className="row justify-content-center">
                <div className="col-md-auto bg-light rounded-lg">
                    <form onSubmit={handleSubmit} className="form-check">
                        <h2 className='text-center'>Авторизация</h2>
                        <div className="row g-1">
                            <div className="col g-3">
                                <label htmlFor="text">Логин:</label>
                            </div>
                            <div className="col g-3">
                                <input
                                    className="form-control"
                                    type="text"
                                    id="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div className="row g-1">
                            <div className="col g-3">
                                <label htmlFor="password" >Пароль:</label>
                            </div>
                            <div className="col g-3">
                                <input
                                    className="form-control"
                                    type="password"
                                    id="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                        <div className="row px-5 p-3">
                            <div className="col">
                                <button type="submit" className="login-button btn btn-outline-primary">Войти</button>
                            </div>
                            <div className="col">
                                <a className="btn btn-outline-primary" href="/registration" role="button">Регистрация</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
  );
};

export default LoginForm;