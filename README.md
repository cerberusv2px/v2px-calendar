# v2px-calendar
Custom calendar for showing events with multiple range events.

### Installation

    implementation 'com.github.cerberusv2px:v2px-calendar:1.0.0'

### Example

```
<com.rosia.v2pxcalendar.ui.CalendarView
    android:id="@+id/calendarView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="8dp"
    android:layout_marginTop="8dp"
    android:layout_marginEnd="8dp"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

Use this in your class file for setting events

```
calendarView.setEvents(getEventDate()).show()


private fun getEventDate(): List<EventModel> {
		return listOf(
			EventModel(
				"2019-07-01",
				"2019-07-01",
				CalendarDateAdapter.STATUS_ACCEPTED
			),
			EventModel(
				"2019-07-02",
				"2019-07-02",
				CalendarDateAdapter.STATUS_REJECTED
			),
			EventModel(
				"2019-07-03",
				"2019-07-04",
				CalendarDateAdapter.STATUS_ACCEPTED
			),
			EventModel(
				"2019-07-08",
				"2019-07-13",
				CalendarDateAdapter.STATUS_PENDING
			)
		)
	}
```

Create a `EventModel` with something like this:
```
data class EventModel(
	val startDate: String,
	val endDate: String? = null,
	val status: String
)

```
