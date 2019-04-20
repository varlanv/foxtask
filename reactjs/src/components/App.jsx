import React, {Component} from 'react';
import _1_RoomList from "./_1_RoomList";
import _2_RoomsByCategory from "./_2_RoomsByCategory";
import _4_Booking from "./_4_Booking";
import _5_BookingsView from "./_5_BookingsView";
import _6_TotalPrice from "./_6_TotalPrice";
import _7_AllBookings from "./_7_AllBookings";

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            services: [],
            bookings: []
        };
    }

    async componentWillMount() {
        await fetch("/extra-services/name").then(response => {
            if (response.ok) {
                response.json()
                    .then(json => {
                        this.setState({services: json});
                    })
            } else {
                this.setState({services: []})
            }
        });

        await fetch(`bookings`)
            .then(response => {
                if (response.ok) {
                    response.json()
                        .then(json => {
                            this.setState({bookings: json});
                        })
                } else {
                    this.setState({bookings: []})
                }
            })
    }

    render() {
        return (
            <div id="main-wrapper">
                <_1_RoomList/>
                <_2_RoomsByCategory/>
                {/*<_3_CreateUser/>*/}
                <_4_Booking services={this.state.services}/>
                <_5_BookingsView/>
                <_6_TotalPrice/>
                <_7_AllBookings/>
            </div>
        );
    }
}

export default App;
