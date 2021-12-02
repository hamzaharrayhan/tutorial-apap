import { Input } from "@mui/material";
import React from "react";
import Buttons from "../button";
import classes from "./styles.module.css";
const Item = props => {
  const {
    id,
    title,
    price,
    description,
    category,
    handleEdit,
    quantity,
    handleDelete,
    handleNumber,
    value,
    onChange,
    count,
  } = props;
  return (
    <div className={classes.item}>
      <h3>{`id: ${id}`}</h3>
      <p>{`Nama Barang: ${title}`}</p>
      <p>{`Harga: ${price}`}</p>
      <p>{`Deskripsi: ${description}`}</p>
      <p>{`Kategori: ${category}`}</p>
      <p>{`stok: ${quantity}`}</p>
      <Buttons action={handleEdit}>Edit</Buttons>
      <br />
      <form>
        <Input
          id={`quantity${id}`}
          type="number"
          placeholder="Number of Items"
          style={{ marginRight: "10px" }}
        ></Input>
        <Buttons action={handleNumber}>Add to Cart</Buttons>
      </form>
    </div>
  );
};
export default Item;
