
export default class WKClass {
    /**
     * construct by routine name
     * @param _routineName
     */
    constructor(_routineName) {
        this.workoutList = []
        this.routineName = _routineName
    }

    /**
     *
     * @param _name (workoutName)
     * @param _setData (WKSetData)
     */
    addToList(_name, _setData) {
        this.workoutList.push({workoutName: _name, workoutSetData: _setData})
    }

}