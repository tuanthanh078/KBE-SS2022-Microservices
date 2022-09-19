import React, { Component } from "react";

class TextField extends Component{
    render(){
        return (
            <div className="textField">
                <label>
                {this.props.label}
                <input type={this.props.type} value={this.props.value} onChange={this.props.onChange} />
                </label>
            </div>
        );
    }
}

export default TextField;