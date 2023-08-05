import { configureStore } from "@reduxjs/toolkit";
import pageSlice from "./slices/pageSlice";
import authSlice from "./slices/authSlice";
import createSagaMiddleWare from "redux-saga";
import mySaga from "./saga";

const sagaMiddleWare = createSagaMiddleWare();
const store = configureStore({
  reducer: {
    page: pageSlice,
    auth: authSlice,
  },
  middleware: [sagaMiddleWare],
});
sagaMiddleWare.run(mySaga);
// const isLogin = store.getState().auth.isLogin;
// console.log(isLogin);
const token = JSON.parse(localStorage.getItem("token"));

if (token) {
  console.log("vao r");
  store.dispatch({
    type: "FETCH_INFO",
  });
}
export default store;
