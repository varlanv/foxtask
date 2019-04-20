import React, {Component} from 'react';
import _1_RoomList from "./_1_RoomList";
import _2_RoomsByCategory from "./_2_RoomsByCategory";
import _3_CreateUser from "./_3_CreateUser";
import _4_Booking from "./_4_Booking";
import _5_BookingsView from "./_5_BookingsView";
import _6_TotalPrice from "./_6_TotalPrice";
import _7_AllBookings from "./_7_AllBookings";

class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            services: []
        };

    }

    async componentWillMount() {
        const req = await fetch("/extra-services/name");
        const body = await req.json();

        this.setState({
            services: body
        })
    }

    render() {
        return (
            <div>
                <_1_RoomList/>
                <_2_RoomsByCategory/>
                <_3_CreateUser/>
                <_4_Booking services={this.state.services}/>
                <_5_BookingsView/>
                <_6_TotalPrice/>
                <_7_AllBookings/>
            </div>
        );
    }
}

export default App;
