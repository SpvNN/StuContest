import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import AppRouter from './router'
import { Provider } from 'react-redux';
import { store } from './store';


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Provider store={store}>
      <AppRouter/>
    </Provider>
  </React.StrictMode>,
)
