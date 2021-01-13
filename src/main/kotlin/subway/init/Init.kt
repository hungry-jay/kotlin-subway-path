package subway.init

import subway.domain.Line
import subway.domain.Section
import subway.domain.Station
import subway.repository.LineRepository
import subway.repository.SectionRepository
import subway.repository.StationRepository

const val GYODAE = "교대역"
const val GANGNAM = "강남역"
const val YEOKSAM = "역삼역"
const val YANGJAE = "양재역"
const val MAEBONG = "매봉역"
const val NAMBU_BUS_TERMINAL = "남부터미널역"
const val YANGJAE_CITIZENS_FOREST = "양재시민의숲역"

const val LINE_NUMBER_TWO = "2호선"
const val LINE_NUMBER_THREE = "3호선"
const val SHINBUNDANG_LINE = "신분당선"

const val ONE = 1
const val TWO = 2
const val THREE = 3
const val FIVE = 5
const val SIX = 6
const val EIGHT = 8
const val TEN = 10

fun init() {
    initStation()
    initLine()
    initSection()
}

fun initStation() {
    StationRepository.addStation(Station(GYODAE))
    StationRepository.addStation(Station(GANGNAM))
    StationRepository.addStation(Station(YEOKSAM))
    StationRepository.addStation(Station(YANGJAE))
    StationRepository.addStation(Station(MAEBONG))
    StationRepository.addStation(Station(NAMBU_BUS_TERMINAL))
    StationRepository.addStation(Station(YANGJAE_CITIZENS_FOREST))
}

fun initLine() {
    LineRepository.addLine(Line(LINE_NUMBER_TWO))
    LineRepository.addLine(Line(LINE_NUMBER_THREE))
    LineRepository.addLine(Line(SHINBUNDANG_LINE))
}

fun initSection() {
    SectionRepository.addSection(Section(GYODAE, GANGNAM, THREE, TWO))
    SectionRepository.addSection(Section(GANGNAM, YEOKSAM, THREE, TWO))
    SectionRepository.addSection(Section(GYODAE, NAMBU_BUS_TERMINAL, TWO, THREE))
    SectionRepository.addSection(Section(NAMBU_BUS_TERMINAL, YANGJAE, FIVE, SIX))
    SectionRepository.addSection(Section(YANGJAE, MAEBONG, ONE, ONE))
    SectionRepository.addSection(Section(GANGNAM, YANGJAE, EIGHT, TWO))
    SectionRepository.addSection(Section(YANGJAE, YANGJAE_CITIZENS_FOREST, THREE, TEN))
}
