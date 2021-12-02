import React from "react";
import APIConfig from "../../api/APIConfig";
import classes from "./styles.module.css";
import CartItem from "../../components/cartItem";
import Buttons from "../../components/button";
import { Link } from "react-router-dom";

class CartItemList extends React.Component {
  constructor(props) {
    super(props);
    this.state = { cartItems: [], items: [], isLoading: false };
    this.handleClickLoading = this.handleClickLoading.bind(this);
    this.handleCheckout = this.handleCheckout.bind(this);
  }

  componentDidMount() {
    this.loadData();
    this.loadDataCart();
  }

  shouldComponentUpdate(nextProps, nextState) {
    console.log("shouldComponentUpdate()");
    return true;
  }

  handleClickLoading() {
    const currentLoading = this.state.isLoading;
    this.setState({ isLoading: !currentLoading });
    console.log(this.state.isLoading);
  }

  async loadData() {
    try {
      const { data } = await APIConfig.get("/item");
      this.setState({ items: data.result });
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async loadDataCart() {
    try {
      const { data } = await APIConfig.get("/cart");
      this.setState({ cartItems: data.result });
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async handleCheckout() {
    await APIConfig.get("/cart/checkout");
    this.loadDataCart();
  }

  render() {
    return (
      <div className={classes.itemList}>
        <h1 className={classes.title}>All Items</h1>
        <div>
          <div style={{ justifyContent: "space-between", display: "flex" }}>
            <Link to="/">
              <Buttons>Back</Buttons>
            </Link>
            {this.state.cartItems.length !== 0 && (
              <Buttons action={this.handleCheckout}>Checkout</Buttons>
            )}
          </div>
          {this.state.cartItems.map(item => (
            <div>
              <CartItem
                key={item.id}
                id={item.id}
                title={item.item.title}
                price={item.item.price}
                jumlah={item.quantity}
                description={item.item.description}
                category={item.item.category}
                quantity={item.item.quantity}
              />
            </div>
          ))}
        </div>
      </div>
    );
  }
}

export default CartItemList;
