import React, { Component } from "react";

const ERROR_MESSAGE = "Please choose one component of each type.";
class ProductCreation extends Component{

    constructor(props){
        super(props);

        let message = (this.props.error === undefined) ? null : this.props.error;
        this.state = {processor: "none", graphics: "none", storage: "none", info: message};

        this.setMessage = this.setMessage.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onProcessorChange = this.onProcessorChange.bind(this);
        this.onGraphicsChange = this.onGraphicsChange.bind(this);
        this.onStorageChange = this.onStorageChange.bind(this);
        this.updateComponents = this.updateComponents.bind(this);

        this.updateComponents();
    }

    setMessage(){
        if(this.props.error === undefined){
            this.setState({info: null});
        }else{
            this.setState({info: this.props.error});
        }
    }

    onSubmit(){
        if(this.state.processor !== "none" && this.state.graphics !== "none" && this.state.storage !== "none"){
            if(typeof this.props.onSubmit === "function"){
                this.props.onSubmit(
                    {
                        processor: this.props.components.find(component => component.id === this.state.processor),
                        graphics: this.props.components.find(component => component.id === this.state.graphics),
                        storage: this.props.components.find(component => component.id === this.state.storage)
                    }
                );
            }
        }else{
            this.setState({info: ERROR_MESSAGE});
        }
    }

    componentDidUpdate(prevProps){
        if(prevProps.components !== this.props.components)
            this.updateComponents();

        if(prevProps.error !== this.props.error)
            this.setMessage();
    }

    updateComponents(){
        this.processors = this.props.components.filter(component => component.type === "processor");
        this.graphics = this.props.components.filter(component => component.type === "graphics");
        this.storages = this.props.components.filter(component => component.type === "storage");

        this.processorOptions = this.processors.map(
            processor => <option value={processor.id} key={processor.id}>{processor.brand + " - " + processor.name}</option>
        );
        this.graphicsOptions = this.graphics.map(
            graphics => <option value={graphics.id} key={graphics.id}>{graphics.brand + " - " + graphics.name}</option>
        );
        this.storageOptions = this.storages.map(
            storage => <option value={storage.id} key={storage.id}>{storage.brand + " - " + storage.name}</option>
        );
    }

    onProcessorChange(event){
        this.setState({processor: event.target.value});
    }

    onGraphicsChange(event){
        this.setState({graphics: event.target.value});
    }

    onStorageChange(event){
        this.setState({storage: event.target.value});
    }

    render(){
        return(
            <div className='productCreation'>
                {this.state.info ?
                    <label className="info">{this.state.info}</label> :
                    null
                }
                <select value={this.state.processor} onChange={this.onProcessorChange}>
                    <option value="none">None</option>
                    {this.processorOptions}
                </select>
                <select value={this.state.graphics} onChange={this.onGraphicsChange}>
                    <option value="none">None</option>
                    {this.graphicsOptions}
                </select>
                <select value={this.state.storage} onChange={this.onStorageChange}>
                    <option value="none">None</option>
                    {this.storageOptions}
                </select>
                <button onClick={this.onSubmit}>Submit</button>
            </div>
        )
    }
}

export default ProductCreation;