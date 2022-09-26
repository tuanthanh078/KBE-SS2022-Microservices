import React, { Component } from "react";

import NavigationBar from "./NavigationBar";
import ElementList from "./ElementList";
import ComponentDetails from "./ComponentDetails";
import ProductDetails from "./ProductDetails";
import ProductCreation from "./ProductCreation";

const ENDPOINT_PRODUCTS = "/products";
const ENDPOINT_COMPONENTS = "/components";
const ENDPOINT_PRODUCTS_CREATE = "/products/custom";

const DISPLAY_PRODUCTS = "products";
const DISPLAY_COMPONENTS = "components";
const DISPLAY_CREATE_PRODUCT = "create";
const DISPLAY_ERROR = "error";

const TYPE_PRODUCTS = "products";
const TYPE_COMPONENTS = "components";

const CURRENCY_US_DOLLAR = "USD";

const MESSAGE_CREATE_FAILED = "Could not create product.";

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
    if(body !== undefined){
        request.setRequestHeader('Content-type', 'multipart/form-data');
        request.send(JSON.stringify(body));
    }else
        request.send();
}

class Main extends Component{
    constructor(props){
        super(props);

        this.state = {display: DISPLAY_PRODUCTS, type: TYPE_PRODUCTS, currency: CURRENCY_US_DOLLAR, products: [], components: [], component: null, product: null, loaded: false};

        this.onSubmit = this.onSubmit.bind(this);
        this.onCurrencyChange = this.onCurrencyChange.bind(this);
        this.onTypeChange = this.onTypeChange.bind(this);
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

        this.getDetails = this.getDetails.bind(this);
        this.getContent = this.getContent.bind(this);
    }

    componentDidMount(){
        requestProducts(this.props.url, this.updateProducts, this.onUpdateFail);
        requestComponents(this.props.url, this.updateComponents, this.onUpdateFail);

        this.displayProducts();
    }

    onSubmit(options){
        if(!this.state.loaded){
            requestProducts(this.props.url, this.updateProducts, this.onUpdateFail);
            requestComponents(this.props.url, this.updateComponents, this.onUpdateFail);
        }

        if(options.type === "products" && this.state.display !== DISPLAY_PRODUCTS){
            this.displayProducts();
        }else if(options.type === "components" && this.state.display !== DISPLAY_COMPONENTS){
            this.displayComponents();
        }
    }

    onCurrencyChange(event){
        this.setState({currency: event.target.value});
    }

    onTypeChange(event){
        let type = event.target.value;
        this.setState({type: type});

        if(!this.state.loaded){
            requestProducts(this.props.url, this.updateProducts, this.onUpdateFail);
            requestComponents(this.props.url, this.updateComponents, this.onUpdateFail);
        }

        if(type === TYPE_PRODUCTS){
            this.displayProducts();
        }else if(type === TYPE_COMPONENTS){
            this.displayComponents();
        }
    }

    displayProducts(){
        this.setState({component: null, display: DISPLAY_PRODUCTS});
    }

    updateProducts(response){
        this.setState({products: JSON.parse(response), loaded: true});
    }

    onSelectProduct(product){
        this.setState({product: product});
    }

    displayComponents(){
        this.setState({product: null, display:DISPLAY_COMPONENTS});
    }

    updateComponents(response){
        this.setState({components: JSON.parse(response), loaded: true});
    }

    onSelectComponent(component){
        this.setState({component: component});
    }

    onUpdateFail(){
        this.setState({display: DISPLAY_ERROR});
    }

    onCreate(){
        this.setState({display: DISPLAY_CREATE_PRODUCT});
    }

    onSubmitProduct(product){
        this.setState({createFailed: false});
        requestCreateProduct(this.props.url, product, this.onCreated, this.onCreateFail);
    }

    onCreated(response){
        console.log("PRODUCT created");
    }

    onCreateFail(){
        this.setState({createFailed: true});
    }

    getDetails(){
        let details;

        if(this.state.component !== null){
            details = <ComponentDetails component={this.state.component} currency={this.state.currency}/>;
        }else if(this.state.product !== null){
            details = <ProductDetails product={this.state.product} currency={this.state.currency}/>;
        }else{
            let id = (this.state.display === DISPLAY_PRODUCTS) ? "productDetails" : "componentDetails";
            details = <div className="details" id={id}/>;
        }

        return details;
    }

    getContent(){
        let content;
        let elements = this.state.components;

        switch(this.state.display){
            case DISPLAY_PRODUCTS:
                elements = this.state.products;
                // fallthrough
            case DISPLAY_COMPONENTS:
                let details = this.getDetails();
                content =
                    <div>
                        <ElementList elements={elements} category={this.state.display} onSelectComponent={this.onSelectComponent} onSelectProduct={this.onSelectProduct}/>
                        {details}
                    </div>
                break;

            case DISPLAY_ERROR:
                content =
                    <label className="error">An error occurred while loading products and components.</label>
                break;

            case DISPLAY_CREATE_PRODUCT:
                content =
                    <ProductCreation components={this.state.components} onSubmit={this.onSubmitProduct} error={this.state.createFailed ? MESSAGE_CREATE_FAILED : undefined}/>
                break;

            default:
                content = null;
        }

        return content;
    }

    render(){
        return(
            <div name='main' className='main'>
                <NavigationBar onSubmit={this.onSubmit} onCreate={this.onCreate} onLogout={this.props.onLogout} type={this.state.type} currency={this.state.currency} onTypeChange={this.onTypeChange} onCurrencyChange={this.onCurrencyChange}/>
                {this.getContent()}
            </div>
        );
    }
}

export default Main;