import {defineStore} from 'pinia'

export const useRecordStore = defineStore('record', {
    state: () => ({
        routineName: '',
        workOutData: [],
        wkSetData: null
    }),
    actions: {
        setData(data) {
            this.wkSetData = data
        },
        setWorkoutData(data){
            this.workOutData = data
        }
        ,
        postCall(url, params) {
            this.axios.post(url, params
            ).catch((error) => {
                return error
            }).then((rep) => {
                return rep
            })
        }
    }
    ,
    getters: {
        getData(state) {
            return state.wkSetData
        }
    }
})
