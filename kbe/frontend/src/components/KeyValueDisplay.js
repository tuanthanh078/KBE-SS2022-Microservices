import React, { Component } from "react";

class KeyValueDisplay extends Component{
    render(){
        return(
            <div className='keyValueDisplay'>
                <label className='key'>{this.props.keyName + ": "}</label>
                <label className='value'>{this.props.value}</label>
            </div>
        );
    }
}

export default KeyValueDisplay;