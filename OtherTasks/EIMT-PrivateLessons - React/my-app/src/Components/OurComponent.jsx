export default function OurComponent(props){

    return (
        <div>
            <h1>{props.person.name} {props.person.surname} {props.age}</h1>
        </div>
    )
}