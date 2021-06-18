import axios from 'axios';
import { getParamsWithAuth } from "@/util/UserUtil"

const host = process.env.VUE_APP_URL;
const port = process.env.VUE_APP_PORT;

const instance = axios.create({
    baseURL: `http://${host}:${port}`,
});

instance.defaults.headers.common['Authorization'] = getParamsWithAuth();
instance.defaults.headers.common['Content-Type'] = 'application/json';


export { instance }