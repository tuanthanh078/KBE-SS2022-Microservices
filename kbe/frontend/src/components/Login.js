import React, { Component } from "react";

import TextField from './TextField';

class Login extends Component{
    constructor(props){
        super(props);
        this.state = {username:"",password:"",info:false};

        this.sendLogin = this.sendLogin.bind(this);
        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
    }

    sendLogin(event){
        console.log('TODO: send http login request');
        console.log('Temporarily hardcoded: user:admin pw:admin');

        this.setState({info:false});
        let valid = true;

        if(!(this.state.username==="admin") || !(this.state.password==="admin")){
            valid = false;
        }

        if(valid === true){
            if(typeof this.props.onLogin === "function"){
                this.props.onLogin(this.state.username);
            }
        }else{
            this.setState({info:true});
        }
        event.preventDefault();
    }

    onUsernameChange(event){
        this.setState({username:event.target.value});
    }

    onPasswordChange(event){
        this.setState({password:event.target.value});
    }

    render(){
        return(
            <div name="login" className="login">
                {this.state.info ?
                    <label className="info">username or password incorrect</label> :
                    null}
                <TextField label="Username" type="text" value={this.state.username} onChange={this.onUsernameChange}/>
                <TextField label="Password" type="password" value={this.state.password} onChange={this.onPasswordChange}/>
                <button onClick={this.sendLogin}>
                    Login
                </button>
            </div>
        );
    }
}

export default Login;