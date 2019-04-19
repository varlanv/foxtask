import React, {Component} from 'react';

class _1_RoomList extends Component {


    constructor(props) {
        super(props);

        this.state = {
            rooms: [],
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
        const {rooms} = this.state;

        return (
            <div className="wrapper">
                <ul>
                    <h2>1. View list of available rooms (room have a number, category, price, additional options like
                        breakfast, cleaning with additional cost) for specified dates.
                    </h2>
                    {rooms.map(room => (
                        <li>
                            {"Room number " + room.number}, {"Type: " + room.name} {"Price: " + room.price}
                        </li>
                    ))}
                </ul>
            </div>
        );
    }

}

export default _1_RoomList;
