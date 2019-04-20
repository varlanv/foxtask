import React, {Component} from 'react';

class _2_RoomsByCategory extends Component {

    constructor(props) {
        super(props);

        this.state = {
            categories: [],
            rooms: [],
            categoryRadio: ""
        };

        this.handleCategoryChange = this.handleCategoryChange.bind(this);
        this.fetchRoomsByCategory = this.fetchRoomsByCategory.bind(this);
    }

    async componentWillMount() {
        const request = await fetch("/categories");
        const body = await request.json();

        this.setState({
            categories: body
        });
    }

    async fetchRoomsByCategory(event) {
        event.preventDefault();
        const request = await fetch(`rooms/category/${this.state.categoryRadio}`);
        const body = await request.json();

        this.setState({
            bookings: body
        })
    }

    handleCategoryChange(event) {
        this.setState({
            categoryRadio: event.target.value
        });
    }

    render() {
        const {categories} = this.state;
        const {rooms} = this.state;

        return (
            <div>
                <form onSubmit={this.fetchRoomsByCategory}>
                    <h2>2. View rooms filtered by category.</h2>
                    {categories.map(category => (<div>
                            <label htmlFor="category-checkbox">{category.name}</label>
                            <input type="radio" className="category-checkbox" name="name" value={category.name}
                                   onChange={this.handleCategoryChange}/>
                        </div>
                    ))}
                    <input type="submit" value="Submit"/>
                </form>
                <div className="wrapper">
                    <ul>
                        {rooms.map(room => (
                            <li>
                                {"Room number " + room.number}, {"Type: " + room.name} {"Price: " + room.price}
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    }
}

export default _2_RoomsByCategory;
