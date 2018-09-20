var NAVTREE =
[
  [ "AXIS Embedded Development SDK", "index.html", [
    [ "Introduction", "index.html#intro", null ],
    [ "Interface overview", "index.html#interfaces_subsec", [
      [ "Media capture interface", "index.html#section_vidcap", null ],
      [ "Parameter interface", "index.html#section_param", null ],
      [ "HTTP interface", "index.html#section_http", null ],
      [ "Event interface", "index.html#section_event", null ],
      [ "Storage interface", "index.html#section_storage", null ],
      [ "PTZ (Pan-Tilt-Zoom) interface", "index.html#section_ptz", null ],
      [ "Serial port interface", "index.html#section_serialport", null ],
      [ "Audio interface", "index.html#section_audio", null ],
      [ "License key interface", "index.html#section_license", null ]
    ] ],
    [ "Web pages overview", "index.html#web_subsec", null ],
    [ "Prerequisites", "prerequisites.html", [
      [ "Required prior knowledge", "prerequisites.html#prior_subsec", null ],
      [ "Technical prerequisites", "prerequisites.html#prereq_subsec", null ]
    ] ],
    [ "Installing the Software Development Kit", "installation.html", [
      [ "Obtaining compilers", "installation.html#install_compiler", null ],
      [ "Installing the compiler", "installation.html#install", null ],
      [ "SDK Contents", "installation.html#sdk_contents", null ]
    ] ],
    [ "Compiling and Running an Application", "compilation.html", [
      [ "Introduction", "compilation.html#Introduction", null ],
      [ "Creating the application package", "compilation.html#package_create", null ],
      [ "Installing the package in the Axis product", "compilation.html#package_install", null ]
    ] ],
    [ "Creating an Application Package", "application_package.html", [
      [ "About application packages", "application_package.html#package_introduction", null ],
      [ "Contents of an application package", "application_package.html#package_contents", null ],
      [ "package.conf", "application_package.html#pack_conf", null ],
      [ "param.conf", "application_package.html#param_format", [
        [ "Parameter types to use for HTML forms", "application_package.html#param_types", null ]
      ] ],
      [ "Saving arbitrary data", "application_package.html#save_data", null ],
      [ "Web related content", "application_package.html#web", [
        [ "The html directory", "application_package.html#html", null ],
        [ "Register CGI path(s)", "application_package.html#httpd_format", null ]
      ] ]
    ] ],
    [ "Debugging", "debugging.html", [
      [ "About the debugger", "debugging.html#about_debugger", null ],
      [ "Installing the debugger on host", "debugging.html#installing_gdb", [
        [ "Artpec-4", "debugging.html#gdb_artpec4", null ],
        [ "Ambarella", "debugging.html#gdb_ambarella", null ]
      ] ],
      [ "Cross compiling and uploading the product before debugging", "debugging.html#cross_compile_debug", null ],
      [ "Starting gdbserver on the Axis product", "debugging.html#gdbserver", null ],
      [ "Set up the GNU debugger or gdb", "debugging.html#mips_gdb", [
        [ "Start debugging", "debugging.html#start_debug", null ]
      ] ],
      [ "Cross compiling for debugging with DUMA", "debugging.html#cross_compile_duma", [
        [ "Using gdb and gdbserver with DUMA", "debugging.html#debug_duma", [
          [ "Application examples using the DUMA memory debugging library", "debugging.html#duma_ex", null ]
        ] ]
      ] ],
      [ "Using the system log or syslog for debugging", "debugging.html#debug_syslog", null ]
    ] ],
    [ "Profiling", "profiling.html", [
      [ "About axis_profile", "profiling.html#about", null ],
      [ "Compilation", "profiling.html#pr_comp", null ],
      [ "Example", "profiling.html#pr_example", null ]
    ] ],
    [ "Example Applications", "example_apps.html", [
      [ "Example applications", "example_apps.html#about_examples", null ],
      [ "Included examples", "example_apps.html#ex_index_toc", [
        [ "axevent - The event interface example applications", "example_apps.html#ex_event", null ],
        [ "axhttp - The http interface example application", "example_apps.html#ex_http", null ],
        [ "licensekey - The license key interface example application", "example_apps.html#ex_license", null ],
        [ "axparameter - The parameter interface example application", "example_apps.html#ex_param", null ],
        [ "axserialport - A serial port interface example application", "example_apps.html#ex_serial", null ],
        [ "axptz - The Pan-Tilt-Zoom (PTZ) interface example application", "example_apps.html#ex_ptz", null ],
        [ "axstorage - The storage interface example application", "example_apps.html#ex_storage", null ],
        [ "axaudio - An audio interface example application", "example_apps.html#ex_audio", null ],
        [ "hello_glib - A less advanced 'Hello world' application", "example_apps.html#ex_hello_glib", null ],
        [ "analyze - A video analytics example application", "example_apps.html#ex_analyze", null ],
        [ "vidcap - The video capture interface example application", "example_apps.html#ex_vidcap", null ]
      ] ]
    ] ],
    [ "Frequently Asked Questions", "faq.html", [
      [ "Frequently asked questions", "faq.html#about_faq", [
        [ "Is the timestamp received with the capture library the same as in the JPEG header?", "faq.html#faq_jpeg_ts", null ],
        [ "Can I find the raw image that corresponds to the JPEG image?", "faq.html#faq_raw_jpeg", null ],
        [ "How do I get information about the product my application is running on?", "faq.html#faq_prod_info", null ],
        [ "What if one of the used interfaces is updated so that my application is no longer compatible?", "faq.html#faq_version_handling", null ],
        [ "What happens with installed applications when performing a firmware upgrade?", "faq.html#faq_firmware_upgrade", null ],
        [ "What happens with installed applications when performing a factory default?", "faq.html#faq_factory_default", null ],
        [ "What happens \"under the hood\" when an application package is installed?", "faq.html#faq_installation_description", null ],
        [ "Why do I get an error code when I use the filters in the rapp library?", "faq.html#faq_rapp_filter_error_message", null ]
      ] ],
      [ "Running the application in host environment", "faq.html#about_running", [
        [ "Using the capture library on host", "faq.html#host_capture", null ],
        [ "Using the fixmath library on host", "faq.html#host_fixmath", null ],
        [ "Using the rapp library on host", "faq.html#host_rapp", null ]
      ] ]
    ] ],
    [ "Appendix 1: Analytics libraries", "analytics_libraries.html", [
      [ "RAster Processing Primitives library (RAPP)", "analytics_libraries.html#sdk_rapp", [
        [ "Example of RAPP use", "analytics_libraries.html#sdk_rapp_example", null ]
      ] ],
      [ "Fixmath library", "analytics_libraries.html#sdk_fixmath", [
        [ "Example of filter implementation with Fixmath", "analytics_libraries.html#sdk_fixmath_example", null ]
      ] ]
    ] ],
    [ "Deprecated List", "deprecated.html", null ],
    [ "API Specification", "files.html", "files" ]
  ] ]
];

var NAVTREEINDEX =
[
"analytics_libraries.html",
"ax__storage_8h.html#a24a6342f43a2463681f58f282cbf41a2adecc4258ba5f99a81dcda0d28df71894",
"faq.html#about_faq"
];

var SYNCONMSG = 'click to disable panel synchronisation';
var SYNCOFFMSG = 'click to enable panel synchronisation';