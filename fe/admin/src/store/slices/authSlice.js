import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isLogin: false,
  is403: false,
  user: {},
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    signIn(state, action) {
      localStorage.setItem("token", JSON.stringify(action.payload));
    },
    setInfo(state, action) {
      state.user = action.payload;
      state.isLogin = true;
      localStorage.removeItem("url");
    },
    signOut(state, action) {
      localStorage.removeItem("token");
      state.isLogin = false;
      state.user = {};
    },
    set403(state, action) {
      state.is403 = true;
    },
    close403(state, action) {
      state.is403 = false;
    },
  },
});

export const { signIn, setInfo, signOut, set403, close403 } = authSlice.actions;
export default authSlice.reducer;
