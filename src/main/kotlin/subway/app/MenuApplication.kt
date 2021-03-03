package subway.app

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.view.getDownwardNameOfSectionToDelete
import subway.view.getDownwardNameOfSectionToRegister
import subway.view.getLineNameOfSectionToDelete
import subway.view.getLineNameOfSectionToRegister
import subway.view.getSectionDistance
import subway.view.getSectionTime
import subway.view.getUpwardNameOfSectionToDelete
import subway.view.getUpwardNameOfSectionToRegister
import subway.view.infoMessage
import subway.view.selectNumber
import subway.view.showAdminLine
import subway.view.showAdminSection
import subway.view.showAdminStation
import subway.view.showAllLines
import subway.view.showAllStations
import subway.view.showCheckPath
import subway.view.showMainPage
import subway.view.showWholeTrack
import subway.view.succeedDeleteSection
import subway.view.succeedRegisterSection

const val MENU_ONE = 1
const val MENU_TWO = 2
const val MENU_THREE = 3
const val MENU_FOUR = 4
const val MENU_FIVE = 5

const val QUIT = "Q"
const val BACK = "B"

var select: String = ""

fun startApp() {
    while (true) {
        showMainPage()
        select = selectNumber()

        if (select == QUIT) break

        when (select.toInt()) {
            MENU_ONE -> adminStation()
            MENU_TWO -> adminLine()
            MENU_THREE -> adminSection()
            MENU_FOUR -> showWholeTrack()
            MENU_FIVE -> checkPath()
        }
    }
}

fun adminStation() {
    showAdminStation()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> registerStation()
        MENU_TWO -> deleteStation()
        MENU_THREE -> showAllStations()
    }
}

fun adminLine() {
    showAdminLine()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> registerLine()
        MENU_TWO -> deleteLine()
        MENU_THREE -> showAllLines()
        MENU_FOUR -> showWholeTrack()
    }
}

fun adminSection() {
    showAdminSection()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> {
            RegisterSection(
                Section(
                    line = Line(getLineNameOfSectionToRegister()),
                    upwardStation = Station(getUpwardNameOfSectionToRegister()),
                    downwardStation = Station(getDownwardNameOfSectionToRegister()),
                    distance = getSectionDistance(),
                    time = getSectionTime()
                )
            ).register()
            infoMessage()
            succeedRegisterSection()
        }
        MENU_TWO -> {
            DeleteSection(
                line = Line(getLineNameOfSectionToDelete()),
                upwardStation = Station(getUpwardNameOfSectionToDelete()),
                downwardStation = Station(getDownwardNameOfSectionToDelete())
            ).delete()
            infoMessage()
            succeedDeleteSection()
        }
    }
}

fun checkPath() {
    showCheckPath()
    select = selectNumber()
    if (select == BACK) return

    when (select.toInt()) {
        MENU_ONE -> shortestPath()
        MENU_TWO -> minimumTime()
    }
}
