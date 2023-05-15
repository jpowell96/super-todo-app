'use client';
// Design taken from Medium.com NextJS Tutorial: https://javascript.plainenglish.io/build-a-simple-todo-app-using-next-js-f88b68761e27
import { ChangeEvent, useState } from 'react';

const TodoApp = () => {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("")
  const [notes, setNotes] = useState("");

  async function saveTodo(createTodoRequest: {title: string , notes: string}) {
    // TODO: Pull the api url from process.env file
    const res = await fetch(`http://localhost:8080/todos/create`, {
      headers: new Headers({'content-type': 'application/json'}),
      method: "POST",
      body: JSON.stringify(createTodoRequest),
      referrer: "",
      referrerPolicy: "origin",
      
    
    });
    console.log(res);
    return res.json();
  }

  const addTodo = async (e) => {
    e.preventDefault();
    if (!title && !notes) return;
    // 1. Write Note to LocalStorage?

    // 2. Call the api
    const note = await saveTodo({title: title, notes: notes});
    console.log(note);
    setTodos([...todos, { id: Date.now(), text: notes, done: false, title: title }]);
    setNotes("");
  };

  const deleteTodo = (id) => {
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  const markTodo = (id) => {
    setTodos(
      todos.map((todo) => (todo.id === id ? { ...todo, done: !todo.done } : todo))
    );
  };

  return (
    <div className="container">
      <h1>Todo App</h1>
      <form onSubmit={addTodo}>
        <p>Title</p>
        <input  
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Todo Title"/>
        <p>Notes</p>
        <input
          type="text"
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
          placeholder="Add a new todo"
        />
        <button type="submit">Add Todo</button>
      </form>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id} className={`todo-item ${todo.done ? "done" : ""}`}>
            {/* TODO: Replace with a Card Component */}
            <h4>{todo.title}</h4>
            {/* TODO: This to be edited and have a separate button to mark as done */}
            <span>{todo.text}</span>
            <button className="complete"onClick={() => markTodo(todo.id)}>Complete</button>
            <button className="delete" onClick={() => deleteTodo(todo.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}


export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
     <TodoApp />
    </main>
  )
}
