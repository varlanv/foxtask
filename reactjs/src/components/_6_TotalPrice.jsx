import React, {Component} from 'react';

class _6_TotalPrice extends Component {

    constructor(props) {
        super(props);

        this.state = {
            price: ""
        };

        this.fetchBookingsByUserId = this.fetchBookingsByUserId.bind(this);
    }

    async fetchBookingsByUserId(event) {
        event.preventDefault();
        const userId = document.getElementById("user-id-inputt").value;

        const request = await fetch(`user/${userId}/bookings/price`);
        const body = await request.json();

        this.setState({
            price: body
        })
    }

    render() {
        const {price} = this.state;

        return (
            <div className="wrapper">
                <h2>6. User can get the total price of the booking (room for dates period + cost of additional options).</h2>
                <form onSubmit={this.fetchBookingsByUserId}>
                    <input id="user-id-inputt" type="text" name="id"/>
                    <input type="submit" name="submit"/>
                </form>
                {price}
            </div>

        );
    }
}

export default _6_TotalPrice;
