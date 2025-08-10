import { useEffect, useState } from 'react'
import './App.css'
import UserList from './Components/UserList'
import OurComponent from './Components/OurComponent'
import { BrowserRouter } from 'react-router'
import { Routes } from 'react-router'
import { Route } from 'react-router'
import Layout from './Components/Layout'

function App() {

  // повик до API до backend-от (до некој RestController)
  // RestController-от враќа податоци кои ќе се чуваат во променлива (тие податоци се state на компонентата)
  // податоците ќе се прикажуваат во html-от
  // врз основа на state-от се овозможува рендерирање на компонентата

  // const [count, setCount] = useState(0)
  // count -> променлива (дел од state на компонентата)
  // setCount -> метод кој ја менува вредноста на count променливата
  // useState(0) -> default вредност на count е 0

  // при промена на state на компонента, таа се ре-рендерира

  // function handleClick() {
  // console.log("Hello World!")
  // setCount(old_value => old_value + 1)
  // }

  // This effect runs after every render (initial & updates).
  // useEffect(() => {
  // This will run after each render where 'count' is updated.
  // console.log("Component rendered or count changed!")
  // })

  // This effect runs only once (after the initial render).
  // useEffect(() => {
  // console.log("Component mounted!")
  // }, []) // Empty dependency array, so it runs only on mount.

  // This effect runs when 'count' changes.
  // useEffect(() => {
  // console.log(`Count has changed to: ${count}`)
  // }, [count]) // Dependency array with 'count', so it runs when count changes.

  return (
    // <OurComponent person={{name:'Ivan', surname:'Pupinoski'}} age={21}/>

    // <div>
    //   <h1>{count}</h1>
    //   <button onClick={handleClick}>Add Count!</button>
    // </div>

    // <UserList />

    <>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Layout />}>
            <Route path='users' element={<UserList />} />
            <Route path='our-component' element={<OurComponent person={{ name: 'Ivan', surname: 'Pupinoski' }} age={21} />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App