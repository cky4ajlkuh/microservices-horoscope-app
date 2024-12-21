import React from 'react';
import LoginForm from './component/LoginForm';
import MainPage from './component/MainPage';
import RegistrationForm from './component/RegistrationForm';
import { Routes, Route } from 'react-router-dom';

function App() {
  return (
  <>
    <Routes>
        <Route path ="/login" element={<LoginForm/>} />
        <Route path ="/registration" element={<RegistrationForm />} />
        <Route path ="/main" element={<MainPage />} />
    </Routes>
</>
  );
}

export default App;