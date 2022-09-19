import React, { Component } from 'react';
import './App.css';

import Login from './components/Login';

const SERVER_URL = 'http://localhost:8085';

class App extends Component {
    constructor(props){
        super(props);
        this.state = {screen: "login"};

        this.onLogin = this.onLogin.bind(this);
        this.getScreen = this.getScreen.bind(this);
    }

    onLogin(username){
        console.log("Logged \"" + username + "\" in.");
        this.setState({screen: "temp", username: username})
    }

    getScreen(){
        if(this.state.screen === "login"){
            return <Login url={SERVER_URL} onLogin={this.onLogin}/>;
        }else if(this.state.screen === "temp"){
            let message = "Welcome " + this.state.username + "!";
            return message;
        }
    }

    render() {
        return this.getScreen();
    }
}

export default App;
