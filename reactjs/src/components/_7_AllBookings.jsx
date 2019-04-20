import React, {Component} from 'react';

class _7_AllBookings extends Component {

    constructor(props) {
        super(props);

        this.state = {
            bookings: [],
        };
    }

    async componentWillMount() {
        const request = await fetch("/bookings");
        const body = await request.json();

        this.setState({
            bookings: body
        });

    }

    render() {
        const {bookings} = this.state;

        return (
            <div>
                <h2>7. View all bookings for the hotel.</h2>

                <div className="wrapper">
                    <ul>
                        {bookings.map(b => (
                            <li className="user-bookings">
                                <p>{`User email:  ${b.user.email}`}</p>
                                <p>{`Room number: ${b.room.number}`}</p>
                                <p>{`Room type: ${b.room.name}`}</p>
                                <p>{`Room price: ${b.room.price}`}</p>
                                <p>{`From: ${b.dateFrom}`}</p>
                                <p>{`To: ${b.dateTo}`}</p>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    }
}

export default _7_AllBookings;
