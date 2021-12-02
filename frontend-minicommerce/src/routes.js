import React from "react";
import { Route, Switch, Redirect } from "react-router-dom";
import ItemList from "./Containers/ItemList";
import CartItemList from "./Containers/CartItemList";

export const AppRoutes = () => {
  return (
    <div>
      <Switch>
        <Route exact path="/" component={ItemList} />
        <Route exact path="/cart-item-list" component={CartItemList} />
        {/* <Route exact path="/">
          <Redirect to="/class-based" />
        </Route> */}
      </Switch>
    </div>
  );
};
