import React, {Component} from 'react';

class _5_BookingsView extends Component {

    constructor(props) {
        super(props);

        this.state = {
            bookings: []
        };

        this.fetchBookingsByUserId = this.fetchBookingsByUserId.bind(this);
    }

    async fetchBookingsByUserId(event) {
        event.preventDefault();
        const userId = document.getElementById("user-id-input").value;

        const request = await fetch(`user/${userId}/bookings`);
        const body = await request.json();

        this.setState({
            bookings: body
        })
    }

    render() {
        const {bookings} = this.state;

        return (
            <div>
                <h2>5. User can view his booking.</h2>
                <form onSubmit={this.fetchBookingsByUserId}>
                    <input id="user-id-input" type="text" name="id"/>
                    <input type="submit" name="submit"/>
                </form>
                <ul>
                    {bookings.map(b => (
                        <li>
                            {JSON.stringify(b)}
                        </li>
                    ))}
                </ul>
            </div>
        );
    }
}


export default _5_BookingsView;
