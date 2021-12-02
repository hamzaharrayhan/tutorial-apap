import React from "react";
import classes from "./styles.module.css";
const Search = props => {
  const { children, onChange, value, placeholder, name } = props;
  return (
    <div>
      <input
        className={classes.input}
        type="text"
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
      >
        {children}
      </input>
    </div>
  );
};
export default Search;
