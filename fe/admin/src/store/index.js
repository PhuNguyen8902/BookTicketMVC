import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./slices/authSlice";
import createSagaMiddleWare from "redux-saga";
import mySaga from "./saga";

const sagaMiddleWare = createSagaMiddleWare();
const store = configureStore({
  reducer: {
    auth: authSlice,
  },
  middleware: [sagaMiddleWare],
});
sagaMiddleWare.run(mySaga);
const token = JSON.parse(localStorage.getItem("token"));

if (token) {
  store.dispatch({
    type: "FETCH_INFO",
  });
}

export default store;
