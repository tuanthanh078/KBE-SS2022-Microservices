import React, { Component } from "react";

class NavigationBar extends Component{
    constructor(props){
        super(props);
        this.state = {type: "products", currency: "USD"};

        this.handleTypeChange = this.handleTypeChange.bind(this);
        this.handleCurrencyChange = this.handleCurrencyChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onCreate = this.onCreate.bind(this);
    }

    handleTypeChange(event){
        this.setState({type: event.target.value});
    }

    handleCurrencyChange(event){
        this.setState({currency: event.target.value});
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

    render(){
        return(
            <div className='bar'>
                <button onClick={this.onCreate}>Create Product</button>
                <select value={this.state.type} onChange={this.handleTypeChange}>
                    <option value="products">Products</option>
                    <option value="components">Components</option>
                </select>
                <select value={this.state.currency} onChange={this.handleCurrencyChange}>
                    <option value="USD">US-Dollar</option>
                    <option value="EUR">Euro</option>
                    <option value="GBP">Pound</option>
                    <option value="JPY">Yen</option>
                    <option value="CHF">Swiss Franc</option>
                </select>
                <button id='buttonSubmit' onClick={this.onSubmit}>Show</button>
            </div>
        );
    }
}

export default NavigationBar;