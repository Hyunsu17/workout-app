import {defineStore} from 'pinia'
import axios from "axios"

export const useRecordStore = defineStore('record', {
    state: () => ({
        WKData: [],
        WKSetData: null,
        tempRoutineStorage: []
    }),
    actions: {
        completeWorkingOut() {
            this.isExercising = false
        },
        startWorkingOut() {
            this.isExercising = true
        }
        ,
        setWkDetailData(data) {
            this.WKSetData = data
        },
        pushToWorkoutData(data) {
            this.WKData.push(data)
        },
        postCall(url, params) {
            return axios.post(url, params
            ).catch((error) => {
                console.log(error)
            }).then((rep) => {
                return rep
            })
        },
        hasWkListData(name) {
            for (let i = 0; this.WKData.length; i++) {
                if (this.WKData[i].routineName === name) return true
            }
            return false
        },
        addToWKList(_idx, _name, _data) {
            this.WKData[_idx].workoutList.push({workoutName: _name, workoutSetData: _data})
        },
        /**
         * 루틴이름으로 WKData List index return
         * @param _name
         * @returns {number|boolean}
         */
        getRoutineIdxByName(_name) {
            for (let i = 0; i < this.WKData.length; i++) {
                if (_name === this.WKData[i].routineName) {
                    return i
                }
            }
            console.log('해당 이름을 가진 루틴이 없습니다')
            return false
        },
        /**
         *
         * @param _routineName
         * @returns {*|null}
         */
        getWKObjectByRoutine(_routineName) {
            for (let i = 0; i < this.WKData.length; i++) {
                console.log(this.WKData[i].routineName)
                if (this.WKData[i].routineName === _routineName)
                    return this.WKData[i]
            }
            console.log('there is no routine like' + _routineName + '!')
            return null
        },
        getWKNameByWKIdx(_routineName, _idx) {
            console.log(_routineName)
            const WKObject = this.getWKObjectByRoutine(_routineName)
            if (WKObject === null) {
                console.log('Sorry WorkOut data is undefined')
            }
            return WKObject.workoutList[_idx].workoutName
        },
        setTempRoutineStorage(_data){
            this.tempRoutineStorage = _data
        },
        getTempRoutineStorage(){
            return this.tempRoutineStorage
        }
    }
    ,
    getters: {
        getWKDetailData(state) {
            return state.WKSetData
        },
        getWKData(state) {
            return state.WKData
        }
    }
})
