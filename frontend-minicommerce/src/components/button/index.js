import { Button } from "@mui/material";
import React from "react";
import classes from "./styles.module.css";

const Buttons = props => {
  const { action, children } = props;
  return (
    <Button variant="contained" onClick={action}>
      {children}
    </Button>
  );
};

export default Buttons;
