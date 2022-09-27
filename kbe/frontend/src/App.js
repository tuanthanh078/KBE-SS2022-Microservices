import React, { Component } from 'react';
import './App.css';

import Login from './components/Login';
import Main from './components/main/Main';

const SERVER_URL = 'http://gateway:8000'

class App extends Component {
    constructor(props){
        super(props);
        this.state = {screen: "main"};

        this.onLogin = this.onLogin.bind(this);
        this.onLogout = this.onLogout.bind(this);
        this.getScreen = this.getScreen.bind(this);
    }

    onLogin(username){
        this.setState({screen: "main"});
    }

    onLogout(){
        this.setState({screen: "login"});
    }

    getScreen(){
        if(this.state.screen === "login"){
            return <Login url={SERVER_URL} onLogin={this.onLogin}/>;
        }else if(this.state.screen === "main"){
            return <Main url={SERVER_URL} onLogout={this.onLogout}/>;
        }
    }

    render() {
        return this.getScreen();
    }
}

export default App;
