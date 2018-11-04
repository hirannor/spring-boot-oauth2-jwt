import React, { Component } from 'react';
import { Nav, Navbar, NavbarBrand, NavItem, NavLink } from 'reactstrap';
import { Link } from 'react-router-dom';

export default class Header extends Component {

  render() {
    return ( 
      <Navbar color="dark" dark expand="md">
        <NavbarBrand tag={Link} to="/">Home</NavbarBrand>
          <Nav navbar>
            <NavItem>
              <NavLink
                tag={Link} to="/users">User Management</NavLink>
            </NavItem>
          </Nav>
      </Navbar>
    );
  }
}