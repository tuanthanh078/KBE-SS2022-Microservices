import React, { Component } from 'react';
import './App.css';

import Login from './components/Login';
import Main from './components/main/Main';

const SERVER_URL = 'http://localhost:8085';

class App extends Component {
    constructor(props){
        super(props);
        this.state = {screen: "main"};

        this.onLogin = this.onLogin.bind(this);
        this.getScreen = this.getScreen.bind(this);
    }

    onLogin(username){
        console.log("Logged \"" + username + "\" in.");
        this.setState({screen: "main", username: username})
    }

    getScreen(){
        if(this.state.screen === "login"){
            return <Login url={SERVER_URL} onLogin={this.onLogin}/>;
        }else if(this.state.screen === "main"){
            return <Main url={SERVER_URL}/>;
        }
    }

    render() {
        return this.getScreen();
    }
}

export default App;
