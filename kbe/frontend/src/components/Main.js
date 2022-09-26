import React, { Component } from "react";

const ENDPOINT_PRODUCTS = "/products";
const ENDPOINT_COMPONENTS = "/components";
const ENDPOINT_PRODUCTS_CREATE = "/products/custom";

class Main extends Component{
    constructor(props){
        super(props);

        this.state = {display: "products", products: [], components: [], component: null, product: null};

        this.onSubmit = this.onSubmit.bind(this);
        this.displayProducts = this.displayProducts.bind(this);
        this.updateProducts = this.updateProducts.bind(this);
        this.onSelectProduct = this.onSelectProduct.bind(this);
        this.displayComponents = this.displayComponents.bind(this);
        this.updateComponents = this.updateComponents.bind(this);
        this.onSelectComponent = this.onSelectComponent.bind(this);
        this.onUpdateFail = this.onUpdateFail.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onSubmitProduct = this.onSubmitProduct.bind(this);
        this.onCreated = this.onCreated.bind(this);
        this.onCreateFail = this.onCreateFail.bind(this);
    }

    componentDidMount(){
        requestProducts(this.props.url, this.updateProducts, this.onUpdateFail);
        requestComponents(this.props.url, this.updateComponents, this.onUpdateFail);

        this.displayProducts();
    }

    onSubmit(options){
        if(options.type === "products" && this.state.display !== "products"){
            this.displayProducts();
        }else if(options.type === "components" && this.state.display !== "components"){
            this.displayComponents();
        }
    }

    displayProducts(){
        this.setState({component: null, display: "products"});
    }

    updateProducts(response){
        this.setState({products: JSON.parse(response)});
    }

    onSelectProduct(product){
        this.setState({product: product});
    }

    displayComponents(){
        this.setState({product: null, display:"components"});
    }

    updateComponents(response){
        this.setState({components: JSON.parse(response)});
    }

    onSelectComponent(component){
        this.setState({component: component});
    }

    onUpdateFail(){
        this.setState({display: "failed"});
    }

    onCreate(){
        this.setState({display: "create"});
    }

    onSubmitProduct(product){
        requestCreateProduct(this.props.url, product, this.onCreated, this.onCreateFail);
    }

    onCreated(response){
        console.log("PRODUCT created");
    }

    onCreateFail(){
        this.setState({createFailed: true});
    }

    render(){
        let content;
        if(this.state.display === "products" || this.state.display === "components"){
            content =
            <div>
                <ElementList elements={(this.state.display === "products") ? this.state.products : this.state.components} category={this.state.display} onSelectComponent={this.onSelectComponent} onSelectProduct={this.onSelectProduct}/>
                {(this.state.component !== null) ?
                    <ComponentDetails component={this.state.component}/> :
                    null
                }
                {(this.state.product !== null) ?
                    <ProductDetails product={this.state.product}/> :
                    null
                }
            </div>
        }else if(this.state.display === "create"){
            content =
            <div>
                <ProductCreation components={this.state.components} onSubmit={this.onSubmitProduct}/>
            </div>
        }else if(this.state.display === "failed"){
            content =
            <label className="error">An error occurred while loading products and components.</label>
        }
        return(
            <div name='main' className='main'>
                <NavigationBar onSubmit={this.onSubmit} onCreate={this.onCreate}/>
                    {content}
            </div>
        );
    }
}

function requestComponents(url, onReceive, onFail){
    sendHtmlRequest("GET", url+ENDPOINT_COMPONENTS, onReceive, onFail);
}
function requestProducts(url, onReceive, onFail){
    sendHtmlRequest("GET", url+ENDPOINT_PRODUCTS, onReceive, onFail);
}
function requestCreateProduct(url, product, onReceive, onFail){
    sendHtmlRequest("POST", url+ENDPOINT_PRODUCTS_CREATE, onReceive, onFail, product);
}
function sendHtmlRequest(method, url, onReceive, onFail, body){
    let request = new XMLHttpRequest();
    request.open(method, url);
    request.onreadystatechange = (e) => {
        if(request.readyState === 4){
            if(request.status >= 200 && request.status < 300){
                onReceive(request.response);
            }else{
                onFail({status: request.status, response: request.response});
            }
        }
    }
    if(body !== undefined)
        request.send(JSON.stringify(body));
    else
        request.send();
}

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

class ElementList extends Component{
    onComponentClick(){
        if(typeof this.component.props.onSelectComponent === "function"){
            this.component.props.onSelectComponent(this.element);
        }
    }

    onProductClick(){
        if(typeof this.component.props.onSelectProduct === "function"){
            this.component.props.onSelectProduct(this.element);
        }
    }

    render(){
        let listElements;
        if(this.props.category === "products"){
            listElements = this.props.elements.map(
                product =>
                    <li key={product.id} onClick={this.onProductClick.bind({component: this, element: product})} className='product'>
                        <div className='productList'>
                            <label id='listProcessor' className='listProduct'>{product.processor.name}</label>
                            <label id='listGraphics' className='listProduct'>{product.graphics.name}</label>
                            <label id='listStorage' className='listProduct'>{product.storage.name}</label>
                        </div>
                    </li>
            );
        }else if(this.props.category === "components"){
            listElements = this.props.elements.map(
                component => <li key={component.id} onClick={this.onComponentClick.bind({component: this, element: component})}>{component.brand + " - " + component.name}</li>
            );
        }

        return(
            <div className='list'>
                <ul>
                    {listElements}
                </ul>
            </div>
        );
    }
}

class ProductDetails extends Component{
    constructor(props){
        super(props);

        this.state = {value: "processor", component: this.props.product.processor};

        this.handleChange = this.handleChange.bind(this);
        this.componentDidUpdate = this.componentDidUpdate.bind(this);
        this.getComponent = this.getComponent.bind(this);
    }

    componentDidUpdate(prevProps){
        if(prevProps.product !== this.props.product){
            let component = this.getComponent(this.state.value);
            this.setState({component: component});
        }
    }

    handleChange(e){
        let component = this.getComponent(e.target.value);
        this.setState({value: event.target.value, component: component});
    }

    getComponent(type){
        let component;
        switch(type) {
            case "processor":
                component = this.props.product.processor;
                break;
            case "graphics":
                component = this.props.product.graphics;
                break;
            case "storage":
                component = this.props.product.storage;
                break;
            default:
        }
        return component;
    }

    render(){
        return(
            <div className='details' id='productDetails'>
                <div className='productHeadline'>
                    <label id='productHeadline'>Product</label>
                    <label id='productHeadlineId'>{" (" + this.props.product.id + ")"}</label>
                    <select value={this.state.value} onChange={this.handleChange} id='productHeadlineSelect'>
                        <option value="processor">Processor</option>
                        <option value="graphics">Graphics</option>
                        <option value="storage">Storage</option>
                    </select>
                </div>
                <ComponentDetails component={this.state.component}/>
            </div>
        );
    }
}

class ComponentDetails extends Component{
    render(){
        return(
            <div className='details' id='componentDetails'>
                <h3>{this.props.component.brand + " - " + this.props.component.name}</h3>
                <KeyValueDisplay keyName='Id' value={this.props.component.id} id='idDetail'/>
                <KeyValueDisplay keyName='Type' value={this.props.component.type}/>
                <KeyValueDisplay keyName='Price' value={this.props.component.price.toFixed(2)}/>
                <KeyValueDisplay keyName='Deliverable' value={this.props.component.deliverable ? "Yes" : "No"}/>
                <KeyValueDisplay keyName='Location' value={this.props.component.location}/>
                <KeyValueDisplay keyName='Dimensions' value={this.props.component.width + "mm, " + this.props.component.length + "mm"}/>
                <KeyValueDisplay keyName='Power' value={this.props.component.power}/>
            </div>
        );
    }
}

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

const ERROR_MESSAGE = "Please choose one component of each type.";
class ProductCreation extends Component{

    constructor(props){
        super(props);

        let message = (this.props.message === undefined) ? ERROR_MESSAGE : this.props.message;
        this.state = {processor: "none", graphics: "none", storage: "none", info: false, infoMessage: message};

        this.setMessage = this.setMessage.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onProcessorChange = this.onProcessorChange.bind(this);
        this.onGraphicsChange = this.onGraphicsChange.bind(this);
        this.onStorageChange = this.onStorageChange.bind(this);
        this.updateComponents = this.updateComponents.bind(this);

        this.updateComponents();
    }

    setMessage(){
        if(this.props.message === undefined){
            this.setState({infoMessage: ERROR_MESSAGE});
        }else{
            this.setState({infoMessage: this.props.message});
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
            this.setState({info: true});
        }
    }

    componentDidUpdate(prevProps){
        if(prevProps.components !== this.props.components)
            this.updateComponents();

        if(prevProps.message !== this.props.message)
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
                    <label className="info">{this.state.infoMessage}</label> :
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

export default Main;