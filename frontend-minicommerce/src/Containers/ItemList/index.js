import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Search from "../../components/search";
import { Badge } from "@mui/material";
import Fab from "@mui/material/Fab";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ViewStreamIcon from "@mui/icons-material/ViewStream";
import { Link } from "react-router-dom";

class ItemList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
      isLoading: false,
      isCreate: false,
      isEdit: false,
      textSearch: "",
      cartItems: [],
      cartHidden: true,
      count: 0,
      id: "",
      title: "",
      price: 0,
      description: "",
      category: "",
      quantity: 0,
    };
    this.handleClickLoading = this.handleClickLoading.bind(this);
    this.handleAddItem = this.handleAddItem.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
    this.handleChangeField = this.handleChangeField.bind(this);
    this.handleEditItem = this.handleEditItem.bind(this);
    this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
    this.handleSubmitItem = this.handleSubmitItem.bind(this);
    this.handleChangeSearch = this.handleChangeSearch.bind(this);
    this.handleChangeNumber = this.handleChangeNumber.bind(this);
    this.handleAddToCart = this.handleAddToCart.bind(this);
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

  handleAddItem() {
    this.setState({ isCreate: true });
  }

  handleCancel(event) {
    event.preventDefault();
    this.setState({ isCreate: false, isEdit: false });
  }

  handleChangeField(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  handleEditItem(item) {
    this.setState({
      isEdit: true,
      id: item.id,
      title: item.title,
      price: item.price,
      description: item.description,
      category: item.category,
      quantity: item.quantity,
    });
  }

  async handleSubmitEditItem(event) {
    event.preventDefault();
    try {
      const data = {
        title: this.state.title,
        price: this.state.price,
        description: this.state.description,
        category: this.state.category,
        quantity: this.state.quantity,
      };
      await APIConfig.put(`/item/${this.state.id}`, data);
      this.setState({
        id: "",
        title: "",
        price: 0,
        description: "",
        category: "",
        quantity: 0,
      });
      this.loadData();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
    this.handleCancel(event);
  }

  async handleSubmitItem(event) {
    event.preventDefault();
    try {
      const data = {
        title: this.state.title,
        price: this.state.price,
        description: this.state.description,
        category: this.state.category,
        quantity: this.state.quantity,
      };
      await APIConfig.post("/item", data);
      this.setState({
        title: "",
        price: 0,
        description: "",
        category: "",
        quantity: 0,
      });
      this.loadData();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
    this.handleCancel(event);
  }

  async handleChangeSearch(event) {
    const { value } = event.target;
    this.setState({ count: value });
    this.loadData();
  }

  async handleChangeNumber(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
    console.log(this.state.count);
  }

  async loadData() {
    try {
      const { data } = await APIConfig.get(
        `/item?title=${this.state.textSearch}`
      );
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

  async handleAddToCart(event) {
    const inputValue = document.getElementById("quantity" + event.id).value;
    const cartItems = [...this.state.cartItems];
    try {
      if (cartItems.length === 0) {
        if (inputValue <= event.quantity) {
          const data = {
            quantity: inputValue,
            idItem: event.id,
          };
          await APIConfig.post("/cart", data);
        }
      } else {
        for (let i = 0; i < cartItems.length; i++) {
          const temp = cartItems[i];
          if (event.id === temp.item.id) {
            if (inputValue <= event.quantity - temp.quantity) {
              const data = {
                quantity: inputValue,
                idItem: event.id,
              };
              await APIConfig.post("/cart", data);
            } else {
              alert("Oops maaf stok tidak cukup");
            }
          } else {
            console.log("masuk else");
            if (inputValue <= event.quantity) {
              const data = {
                quantity: inputValue,
                idItem: event.id,
              };
              await APIConfig.post("/cart", data);
            }
          }
        }
      }
      this.loadData();
      this.loadDataCart();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  render() {
    console.log("render()");
    return (
      <div className={classes.itemList}>
        <h1 className={classes.title}>All Items</h1>
        <div style={{ position: "fixed", top: 25, right: 25 }}>
          <Link to={{ pathname: "/cart-item-list" }}>
            <Fab variant="extended">
              <Badge
                color="secondary"
                badgeContent={this.state.cartItems.length}
              >
                <ShoppingCartIcon />
              </Badge>
            </Fab>
          </Link>
        </div>
        <Search
          className={classes.textField}
          placeholder="Cari Item"
          name="textSearch"
          value={this.state.textSearch}
          onChange={this.handleChangeSearch}
        />
        <Button action={this.handleAddItem}>Add Item</Button>
        <div>
          {this.state.items.map(item => (
            <div>
              <Item
                key={item.id}
                id={item.id}
                title={item.title}
                price={item.price}
                description={item.description}
                category={item.category}
                quantity={item.quantity}
                handleEdit={() => this.handleEditItem(item)}
                handleNumber={() => this.handleAddToCart(item)}
              />
            </div>
          ))}
        </div>
        <Modal
          show={this.state.isCreate || this.state.isEdit}
          handleCloseModal={this.handleCancel}
          modalTitle={
            this.state.isCreate ? "Add Item" : `Edit item ID ${this.state.id}`
          }
        >
          <form>
            <input
              className={classes.textField}
              type="text"
              placeholder="Nama Item"
              name="title"
              onChange={this.handleChangeField}
              value={this.state.title}
            ></input>

            <input
              className={classes.textField}
              type="number"
              placeholder="Harga"
              name="price"
              value={this.state.price}
              onChange={this.handleChangeField}
            ></input>

            <textarea
              className={classes.textField}
              placeholder="Deskripsi"
              name="description"
              rows="4"
              onChange={this.handleChangeField}
              value={this.state.description}
            ></textarea>

            <input
              className={classes.textField}
              type="text"
              placeholder="Kategori"
              name="category"
              onChange={this.handleChangeField}
              value={this.state.category}
            ></input>

            <input
              className={classes.textField}
              type="number"
              placeholder="qty"
              name="quantity"
              onChange={this.handleChangeField}
              value={this.state.quantity}
            ></input>
            {this.state.isCreate ? (
              <Button action={this.handleSubmitItem}>Create</Button>
            ) : (
              <Button action={this.handleSubmitEditItem}>Update</Button>
            )}
            <Button action={this.handleCancel}>Cancel</Button>
          </form>
        </Modal>
      </div>
    );
  }
}
export default ItemList;
