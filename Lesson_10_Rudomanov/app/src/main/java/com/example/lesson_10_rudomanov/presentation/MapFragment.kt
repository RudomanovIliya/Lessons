package com.example.lesson_10_rudomanov.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_10_rudomanov.R
import com.example.lesson_10_rudomanov.data.ApiClient
import com.example.lesson_10_rudomanov.data.model.Bridge
import com.example.lesson_10_rudomanov.databinding.FragmentMapBinding
import com.example.lesson_10_rudomanov.databinding.ViewMapPinBinding
import com.example.lesson_10_rudomanov.presentation.extensions.toBitmap
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.ClusterListener
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val ONE_HOUR = 60 * 60 * 1000
private const val YANDEX_ZOOM_REDUCTION_COEFFICIENT = 0.8f

class MapFragment : Fragment(R.layout.fragment_map) {
    private val binding by viewBinding(FragmentMapBinding::bind)
    private val viewModel: MapViewModel by viewModels()
    private val mapPinSize by lazy { resources.getDimensionPixelSize(R.dimen.map_pin_size) }
    private val mapObjects = mutableMapOf<PlacemarkMapObject, Bridge>()
    private val mapPinViewBinding by lazy { ViewMapPinBinding.inflate(layoutInflater) }
    private val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    private val currentDate = Date()
    private val timeText: String = timeFormat.format(currentDate)
    private val timeDate: Date? = timeFormat.parse(timeText)

    private val currentDatePlusHour = System.currentTimeMillis() + ONE_HOUR
    private val timeTextPlusHour: String = timeFormat.format(currentDatePlusHour)
    private val timeDatePlusHour: Date? = timeFormat.parse(timeTextPlusHour)
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val inputListener =
        (object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                binding.cardViewInfo.visibility = View.GONE
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
                TODO("Not yet implemented")
            }
        })
    private val tapListener = (MapObjectTapListener { mapObject, _ ->
        mapObjects[mapObject]?.let { bridge ->
            binding.cardViewInfo.visibility = View.VISIBLE
            when (bridge.stateBridge) {
                0 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_soon)
                1 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_normal)
                2 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_late)
            }
            binding.textViewTitle.text = bridge.name
            binding.textViewTime.text = ""
            bridge.divorces?.forEach { position ->
                val stringBuilderTime = StringBuilder()
                stringBuilderTime.append(position.start).append(" - ")
                    .append(position.end)
                    .append("    ")
                binding.textViewTime.append(stringBuilderTime)
            }
        }
        true
    })
    private val clusterListener = ClusterListener { cluster ->
        mapPinViewBinding.textViewName.text = cluster.size.toString()
        cluster.addClusterTapListener { selectedCluster ->
            val points = selectedCluster.placemarks.map { it.geometry }
            val boundingBoxBuilder = BoundingBoxBuilder().apply {
                points.forEach { point ->
                    include(point)
                }
            }
            val boundingBoxCameraPosition = binding.mapView.mapWindow.map.cameraPosition(
                Geometry.fromBoundingBox(boundingBoxBuilder.build()),
                0f,
                0f,
                binding.mapView.mapWindow.focusRect
            )
            val targetCameraPosition = CameraPosition(
                boundingBoxCameraPosition.target,
                boundingBoxCameraPosition.zoom - YANDEX_ZOOM_REDUCTION_COEFFICIENT,
                boundingBoxCameraPosition.azimuth,
                boundingBoxCameraPosition.tilt,
            )
            binding.mapView.mapWindow.map.move(targetCameraPosition)
            true
        }
        cluster.appearance.setIcon(
            ImageProvider.fromBitmap(mapPinViewBinding.root.toBitmap(mapPinSize))
        )
    }
    private var collection: ClusterizedPlacemarkCollection? = null
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted ->
        val isGranted = granted.values.any()
        if (isGranted) {
            youPosition()
        }
    }

    @SuppressLint("MissingPermission")
    private fun youPosition() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(binding.root.context)
        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            })
            .addOnSuccessListener { location: android.location.Location? ->
                var placemarckLocation: PlacemarkMapObject? = null
                if (location != null) {
                    if (placemarckLocation != null) {
                        binding.mapView.mapWindow.map.mapObjects.remove(placemarckLocation)
                    }
                    placemarckLocation =
                        binding.mapView.mapWindow.map.mapObjects.addPlacemark().apply {
                            geometry =
                                Point(location.latitude, location.longitude)
                            mapPinViewBinding.textViewName.text = getString(R.string.you)
                            setIcon(
                                ImageProvider.fromBitmap(
                                    mapPinViewBinding.root.toBitmap(mapPinSize)
                                )
                            )
                        }
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.mapJob()
        }
        MapKitFactory.initialize(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        binding.mapView.mapWindow.map.move(
            CameraPosition(Point(59.939136, 30.344459), 13f, 0f, 0f)
        )
        binding.buttonRepeat.setOnClickListener {
            viewModel.mapJob()
            loadBridges()
        }

        binding.mapView.mapWindow.map.addInputListener(inputListener)
        collection = binding.mapView.mapWindow.map.mapObjects
            .addClusterizedPlacemarkCollection(clusterListener)
        loadBridges()
    }

    private fun stateBridge(bridge: Bridge?) {
        if (bridge != null) {
            if (bridge.divorces != null) {
                for (position in bridge.divorces) {
                    if (position.start != null && position.end != null && timeDate != null && timeDatePlusHour != null) {
                        val bridgeStartTime: Date? = timeFormat.parse(position.start)
                        val bridgeEndTime: Date? = timeFormat.parse(position.end)
                        if (timeDate.before(bridgeStartTime) || (timeDate.after(
                                bridgeEndTime
                            ))
                        ) {
                            if (timeDatePlusHour.after(bridgeStartTime) && (timeDate.before(
                                    bridgeEndTime
                                ))
                            ) {
                                bridge.stateBridge = 0
                                break
                            } else {
                                bridge.stateBridge = 1
                            }
                        } else {
                            bridge.stateBridge = 2
                            break
                        }
                    }
                }
            }
        }
    }

    private fun loadBridges() {
        viewModel.listLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadState.Data -> {
                    binding.progressBar.isVisible = false
                    if (state.data != null) {
                        state.data.forEach { bridge ->
                            stateBridge(bridge)
                            collection?.addPlacemark()?.apply {
                                if (bridge.lat != null && bridge.lng != null) {
                                    geometry = Point(bridge.lat, bridge.lng)
                                }
                                when (bridge.stateBridge) {
                                    0 -> setIcon(
                                        ImageProvider.fromBitmap(
                                            binding.root.context.getDrawable(R.drawable.ic_brige_soon)
                                                ?.toBitmap()
                                        )
                                    )

                                    1 -> setIcon(
                                        ImageProvider.fromBitmap(
                                            binding.root.context.getDrawable(R.drawable.ic_brige_normal)
                                                ?.toBitmap()
                                        )
                                    )

                                    2 -> setIcon(
                                        ImageProvider.fromBitmap(
                                            binding.root.context.getDrawable(R.drawable.ic_brige_late)
                                                ?.toBitmap()
                                        )
                                    )
                                }
                                addTapListener(tapListener)
                                mapObjects[this] = bridge
                            }
                        }
                        collection?.clusterPlacemarks(40.0, 15)
                    } else {
                        binding.progressBar.isVisible = false
                        binding.mapView.visibility = View.GONE
                        binding.textViewError.visibility = View.VISIBLE
                        binding.textViewError.text = getString(R.string.data_not_download)
                        binding.buttonRepeat.visibility = View.VISIBLE
                    }
                }

                is LoadState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.mapView.visibility = View.GONE
                    binding.textViewError.visibility = View.VISIBLE
                    binding.textViewError.text = state.toString()
                }

                is LoadState.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        binding.mapView.mapWindow.map.mapObjects.clear()
        super.onDestroyView()
    }
}