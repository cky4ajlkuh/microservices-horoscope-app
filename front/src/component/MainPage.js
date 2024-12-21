import React, { useState, useEffect, useRef } from 'react';
import Select from 'react-select';
import axios from 'axios';
import {useNavigate} from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const options = [
      { value: '1', label: 'Овен' },
      { value: '2', label: 'Телец' },
      { value: '3', label: 'Близнецы' },
      { value: '4', label: 'Рак' },
      { value: '5', label: 'Лев' },
      { value: '6', label: 'Дева' },
      { value: '7', label: 'Весы' },
      { value: '8', label: 'Скорпион' },
      { value: '9', label: 'Стрелец' },
      { value: '10', label: 'Козерог' },
      { value: '11', label: 'Водолей' },
      { value: '12', label: 'Рыбы' },
];

const Item = ({ title, description, url, urlToImage }) => (
    <div className="row">
        <div className="col">
            <img src={urlToImage}  className="img-fluid img-thumbnail"/>
        </div>
        <div className="col">
            <h3>{title}</h3>
            <p>{description}</p>
            <a href={url}>Read more</a>
        </div>
    </div>
);

const MainPage = () => {
    const [selectedOption, setSelectedOption] = useState(null);
    const [name, setName] = useState(null);
    const [zodiac, setZodiac] = useState(null);
    const [birthday, setBirthday] = useState(null);
    const [day, setDay] = useState("TODAY");
    const [horoscopeText, setHoroscopeText] = useState("");
    const [news, setNews] = useState("");
    const [newsUrl, setNewsUrl] = useState("");
    const navigator = useNavigate();
    const formRef = useRef(null);

    const showError = (message) => {
        toast.error(message);
    };

    useEffect(() => {
        const storedName = localStorage.getItem('name');
        const storedZodiac = localStorage.getItem('zodiac');
        const storedBirthday = localStorage.getItem('birthday');
        if (storedName) {
            setName(storedName);
        }
        if (storedZodiac) {
            setZodiac(storedZodiac);
        }
        if (storedBirthday) {
            setBirthday(storedBirthday);
        }
    }, []);

    const handleTodayClick = () => {
        setDay('TODAY');
    };

    const handleTomorrowClick = () => {
        setDay('TOMORROW');
    };

    const handleYesterdayClick = () => {
        setDay('YESTERDAY');
    };

    const handleWallStreetJournalNews = () => {
        setNewsUrl('WallStreetJournal');
    };

    const handleTeslaNews = () => {
        setNewsUrl('Tesla');
    };

    const handleTechCrunchNews = () => {
        setNewsUrl('TechCrunch');
    };

    const getHoroscope = (e) => {
        e.preventDefault();
        let sign = zodiac;
        if (selectedOption) {
            sign = selectedOption.value;
        }
        const data = {day, sign};
        axios.post('api/horoscope/getHoroscope', data, {
            headers: {
                'Content-Type': 'application/json',
            }
        })
        .then(response => {
            if (response.data != null) {
                setHoroscopeText(response.data.horoscope);
            } else {
                showError(response.data.error);
            }
        })
        .catch(error => {
            showError(error);
        });
    };

    const getNews = (e) => {
        e.preventDefault();
        axios.get('api/news/' + newsUrl)
        .then(response => {
            if (response.data != null) {
                setNews(response.data);
            } else {
                showError(response)
            }
        })
        .catch(error => {
            showError(error);
        });
    };

    const exitHandler = (e) => {
        e.preventDefault();
        localStorage.clear();
        navigator('/login');
    };

    return (
        <div className= "container">
            <ToastContainer />
            <nav className="navbar navbar-expand-lg navbar-light bg-info rounded">
                <div className="container-fluid">
                    <a className="navbar-brand">{name}</a>
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <form onSubmit={exitHandler}>
                            <button className="login-button btn btn-danger me-2">Выход</button>
                            </form>
                        </li>
                    </ul>
              </div>
            </nav>
            <div className="col bg-light">
                <div className="row justify-content-center">
                    <div className="col-3 mt-5">
                      <img src={"https://horo.mail.ru/dist/images/zodiac/360/" + zodiac + ".png"} alt="Description of image" />
                    </div>
                    <div className="col-5">
                        <div className="col">
                            <div className="row">
                                <p className="mt-4">Вы родились {birthday}, ваш знак гороскопа {options[zodiac !== null ? zodiac : 0].label}</p>
                            </div>
                            <nav className="navbar navbar-dark ">
                                <form ref={formRef} onSubmit={getHoroscope} className="container-fluid justify-content-start">
                                    <button className="login-button btn btn-info me-2"
                                    onClick={handleTodayClick}>Сегодня</button>
                                    <button className="login-button btn btn-info me-2"
                                    type="submit" onClick={handleTomorrowClick}>Завтра</button>
                                    <button className="login-button btn btn-info me-2"
                                    type="submit" onClick={handleYesterdayClick}>Вчера</button>
                                    <Select className="me-2"
                                        value={selectedOption}
                                        onChange={(option) => setSelectedOption(option)}
                                        options={options}
                                    />
                                    <button className="login-button btn btn-info"
                                    type="submit">Найти</button>
                                </form>
                            </nav>
                            <div>
                                <p className="fw-bolder bg-light rounded">{horoscopeText ? horoscopeText :
                                "Выберите день, на который вы хотели бы увидеть гороскоп."}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row justify-content-center mt-4">
                    <p className="fw-bolder text-center">Мировые новости</p>
                    <form onSubmit={getNews} className="container-fluid justify-content-start">
                        <div className="row">
                            <div className="col">
                                <div className="text-center">
                                    <button className="login-button btn btn-info me-2"
                                    type="submit" onClick={handleWallStreetJournalNews}>Wall Street Journal</button>
                                    <button className="login-button btn btn-info me-2"
                                    type="submit" onClick={handleTeslaNews}>All articles about Tesla</button>
                                    <button className="login-button btn btn-info me-2"
                                    type="submit" onClick={handleTechCrunchNews}>Top headlines from TechCrunch</button>
                                </div>
                            </div>
                        </div>
                        <div className="mt-4">
                            {news !== "" ? news.map((item, index) => (
                                <Item className="mt-4" key={index} title={item.title} description={item.description} url={item.url} urlToImage={item.urlToImage}/>
                            )) : ""}
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default MainPage;