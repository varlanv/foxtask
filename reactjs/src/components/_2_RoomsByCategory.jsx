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
        await fetch(`rooms/category/${this.state.categoryRadio}`)
            .then(response => {
                if (response.ok) {
                    response.json()
                        .then(json => {
                            this.setState({rooms: json});
                        })
                } else {
                    this.setState({rooms: []})
                }
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
                <h2>2. View rooms filtered by category.</h2>
                <div className="wrapper">
                    <form onSubmit={this.fetchRoomsByCategory}>
                        {categories.map(category => (<div>
                                <label htmlFor="category-checkbox">{category.name}</label>
                                <input type="radio" className="category-checkbox" name="name"
                                       value={category.name}
                                       onChange={this.handleCategoryChange}/>
                            </div>
                        ))}
                        <input type="submit" value="Submit"/>
                    </form>
                    <div>
                        <ul>
                            {rooms.map(room => (
                                <li className="room-list">
                                    <p>{`Room number: ${room.number}`}</p>
                                    <p>{`Room type: ${room.name}`}</p>
                                    <p>{`Room price: ${room.price}`}</p>
                                    <p>{`Available: ${room.available}`}</p>

                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}

export default _2_RoomsByCategory;
