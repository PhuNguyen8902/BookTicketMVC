import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isLogin: false,
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
    },
    signOut(state, action) {
      localStorage.removeItem("token");
      state.isLogin = false;
      state.user = {};
    },
  },
});

export const { signIn, setInfo, signOut } = authSlice.actions;
export default authSlice.reducer;
