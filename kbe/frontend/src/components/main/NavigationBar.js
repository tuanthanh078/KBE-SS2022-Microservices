import React, { Component } from "react";

class NavigationBar extends Component{
    constructor(props){
        super(props);

        this.handleTypeChange = this.handleTypeChange.bind(this);
        this.handleCurrencyChange = this.handleCurrencyChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onLogout = this.onLogout.bind(this);
    }

    handleTypeChange(event){
        if(typeof this.props.onTypeChange === "function")
            this.props.onTypeChange(event);
    }

    handleCurrencyChange(event){
        if(typeof this.props.onCurrencyChange === "function")
            this.props.onCurrencyChange(event);
    }

    onSubmit(){
        if(typeof this.props.onSubmit === "function"){
            this.props.onSubmit({type: this.state.type, currency: this.state.currency});
        }
    }

    onCreate(){
        if(typeof this.props.onCreate === "function"){
            this.props.onCreate();
        }
    }

    onLogout(){
        if(typeof this.props.onLogout === "function"){
            this.props.onLogout();
        }
    }

    render(){
        return(
            <div className='bar'>
                <button onClick={this.onCreate}>Create Product</button>
                <select value={this.props.type} onChange={this.handleTypeChange}>
                    <option value="products">Products</option>
                    <option value="components">Components</option>
                </select>
                <select value={this.props.currency} onChange={this.handleCurrencyChange}>
                    <option value="USD">US-Dollar</option>
                    <option value="EUR">Euro</option>
                    <option value="GBP">Pound</option>
                    <option value="JPY">Yen</option>
                    <option value="CHF">Swiss Franc</option>
                </select>
                <button onClick={this.onLogout}>Logout</button>
            </div>
        );
    }
}

export default NavigationBar;