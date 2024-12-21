import {BrowserRouter as Router}  from "react-router-dom";
import ReactDOM from 'react-dom';
import React from 'react';
import App from './App';
import 'C:/Users/Господин/Music/front/node_modules/bootstrap/dist/css/bootstrap.min.css'

ReactDOM.render(
  <React.StrictMode>
  <Router>
    <App />
    </Router>
  </React.StrictMode>,
  document.getElementById("root")
);