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
        const userEmail = document.getElementById("user-email-inp").value;

        await fetch(`user/${userEmail}/bookings/price`).then(response => {
            if (response.ok) {
                response.json()
                    .then(json => {
                        this.setState({price: `Total price:  ${json}`});
                    })
            } else {
                this.setState({
                    price: ""
                })

            }
        });

    }

    render() {
        const price = this.state.price;

        return (
            <div>
                <h2>6. User can get the total price of the booking (room for dates period + cost of additional options).
                </h2>
                <div className="wrapper">
                    <form onSubmit={this.fetchBookingsByUserId}>
                        <input id="user-email-inp" type="text" name="id" placeholder="user email"/>
                        <input type="submit" name="submit"/>
                    </form>
                    {price}
                </div>
            </div>

        )
    }
}

export default _6_TotalPrice;
