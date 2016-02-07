CREATE EXTERNAL TABLE IF NOT EXISTS affiliation(key string,id string ,projectid string ,resprojectid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:projectid,CF:resprojectid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Affiliation");
CREATE EXTERNAL TABLE IF NOT EXISTS bedinventory(key string,id string ,bed_inventory string ,ch_bed_inventory string ,vet_bed_inventory string ,youth_age_group string ,youth_bed_inventory string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:bed_inventory,CF:ch_bed_inventory,CF:vet_bed_inventory,CF:youth_age_group,CF:youth_bed_inventory,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Bedinventory");
CREATE EXTERNAL TABLE IF NOT EXISTS client(key string,id string ,dedup_client_id string ,name_data_quality string ,ssn_data_quality string ,dob timestamp ,dob_data_quality string ,gender string ,other_gender string ,ethnicity string ,race string ,veteran_status string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:dedup_client_id,CF:name_data_quality,CF:ssn_data_quality,CF:dob,CF:dob_data_quality,CF:gender,CF:other_gender,CF:ethnicity,CF:race,CF:veteran_status,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Client");
CREATE EXTERNAL TABLE IF NOT EXISTS commercialsexualexploitation(key string,askedorforcedtoexchangeforsex string ,id string ,countofexchangeforsex string ,exchangeforsexpastthreemonths string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:askedorforcedtoexchangeforsex,CF:id,CF:countofexchangeforsex,CF:exchangeforsexpastthreemonths,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Commercialsexualexploitation");
CREATE EXTERNAL TABLE IF NOT EXISTS connectionwithsoar(key string,connectionwithsoar string ,id string ,exitid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:connectionwithsoar,CF:id,CF:exitid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Connectionwithsoar");
CREATE EXTERNAL TABLE IF NOT EXISTS dateofengagement(key string,dateofengagement timestamp ,id string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateofengagement,CF:id,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Dateofengagement");
CREATE EXTERNAL TABLE IF NOT EXISTS disabilities(key string,id string ,disabilityresponse string ,disabilitytype string ,documentationonfile string ,indefiniteandimpairs string ,pathhowconfirmed string ,pathsmiinformation string ,enrollmentid string ,receivingservices string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:disabilityresponse,CF:disabilitytype,CF:documentationonfile,CF:indefiniteandimpairs,CF:pathhowconfirmed,CF:pathsmiinformation,CF:enrollmentid,CF:receivingservices,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Disabilities");
CREATE EXTERNAL TABLE IF NOT EXISTS domesticviolence(key string,id string ,domesticviolencevictim string ,enrollmentid string ,whenoccurred string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:domesticviolencevictim,CF:enrollmentid,CF:whenoccurred,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Domesticviolence");
CREATE EXTERNAL TABLE IF NOT EXISTS employment(key string,id string ,information_date timestamp ,employed string ,employment_type string ,not_employed_reason string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:information_date,CF:employed,CF:employment_type,CF:not_employed_reason,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Employment");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment(key string,id string ,continuouslyhomelessoneyear string ,disablingcondition string ,entrydate timestamp ,householdid string ,housingstatus string ,monthshomelesspastthreeyears string ,monthshomelessthistime string ,otherresidenceprior string ,personalid string ,projectentryid string ,projectid string ,relationshiptohoh string ,residenceprior string ,residencepriorlengthofstay string ,statusdocumented string ,timeshomelesspastthreeyears string ,yearshomeless string ,client_id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:continuouslyhomelessoneyear,CF:disablingcondition,CF:entrydate,CF:householdid,CF:housingstatus,CF:monthshomelesspastthreeyears,CF:monthshomelessthistime,CF:otherresidenceprior,CF:personalid,CF:projectentryid,CF:projectid,CF:relationshiptohoh,CF:residenceprior,CF:residencepriorlengthofstay,CF:statusdocumented,CF:timeshomelesspastthreeyears,CF:yearshomeless,CF:client_id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Enrollment");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment_coc(key string,id string ,project_co_id string ,enrollmentid string ,coc_code string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:project_co_id,CF:enrollmentid,CF:coc_code,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "EnrollmentCoc");
CREATE EXTERNAL TABLE IF NOT EXISTS exit(key string,destination string ,exitdate timestamp ,id string ,otherdestination string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:destination,CF:exitdate,CF:id,CF:otherdestination,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Exit");
CREATE EXTERNAL TABLE IF NOT EXISTS exithousingassessment(key string,id string ,exitid string ,housingassessment string ,subsidyinformation string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:exitid,CF:housingassessment,CF:subsidyinformation,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Exithousingassessment");
CREATE EXTERNAL TABLE IF NOT EXISTS exitplansactions(key string,assistancemainstreambenefits string ,exitcounseling string ,exitid string ,id string ,furtherfollowupservices string ,otheraftercareplanoraction string ,permanenthousingplacement string ,resourcepackage string ,scheduledfollowupcontacts string ,temporaryshelterplacement string ,writtenaftercareplan string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assistancemainstreambenefits,CF:exitcounseling,CF:exitid,CF:id,CF:furtherfollowupservices,CF:otheraftercareplanoraction,CF:permanenthousingplacement,CF:resourcepackage,CF:scheduledfollowupcontacts,CF:temporaryshelterplacement,CF:writtenaftercareplan,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Exitplansactions");
CREATE EXTERNAL TABLE IF NOT EXISTS familyreunification(key string,exitid string ,familyreunificationachieved string ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:exitid,CF:familyreunificationachieved,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Familyreunification");
CREATE EXTERNAL TABLE IF NOT EXISTS formerwardchildwelfare(key string,childwelfaremonths string ,childwelfareyears string ,formerwardchildwelfare string ,id string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:childwelfaremonths,CF:childwelfareyears,CF:formerwardchildwelfare,CF:id,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Formerwardchildwelfare");
CREATE EXTERNAL TABLE IF NOT EXISTS formerwardjuvenilejustice(key string,id string ,formerwardjuvenilejustice string ,juvenilejusticemonths string ,juvenilejusticeyears string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:formerwardjuvenilejustice,CF:juvenilejusticemonths,CF:juvenilejusticeyears,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Formerwardjuvenilejustice");
CREATE EXTERNAL TABLE IF NOT EXISTS funder(key string,id string ,enddate timestamp ,funder string ,grantid string ,projectid string ,startdate timestamp ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enddate,CF:funder,CF:grantid,CF:projectid,CF:startdate,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Funder");
CREATE EXTERNAL TABLE IF NOT EXISTS health_status(key string,id string ,enrollmentid string ,information_date timestamp ,health_category string ,health_status string ,due_date timestamp ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:information_date,CF:health_category,CF:health_status,CF:due_date,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "HealthStatus");
CREATE EXTERNAL TABLE IF NOT EXISTS healthinsurance(key string,cobra string ,employerprovided string ,id string ,insurancefromanysource string ,medicaid string ,medicare string ,nocobrareason string ,noemployerprovidedreason string ,nomedicaidreason string ,nomedicarereason string ,noprivatepayreason string ,noschipreason string ,nostatehealthinsreason string ,novamedreason string ,privatepay string ,enrollmentid string ,schip string ,statehealthins string ,vamedicalservices string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:cobra,CF:employerprovided,CF:id,CF:insurancefromanysource,CF:medicaid,CF:medicare,CF:nocobrareason,CF:noemployerprovidedreason,CF:nomedicaidreason,CF:nomedicarereason,CF:noprivatepayreason,CF:noschipreason,CF:nostatehealthinsreason,CF:novamedreason,CF:privatepay,CF:enrollmentid,CF:schip,CF:statehealthins,CF:vamedicalservices,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Healthinsurance");
CREATE EXTERNAL TABLE IF NOT EXISTS housingassessmentdisposition(key string,assessmentdisposition string ,exitid string ,id string ,otherdisposition string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assessmentdisposition,CF:exitid,CF:id,CF:otherdisposition,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Housingassessmentdisposition");
CREATE EXTERNAL TABLE IF NOT EXISTS incomeandsources(key string,alimony string ,alimonyamount string ,childsupport string ,childsupportamount string ,earned string ,earnedamount string ,ga string ,gaamount string ,id string ,incomefromanysource string ,othersource string ,othersourceamount string ,othersourceidentify string ,pension string ,pensionamount string ,privatedisability string ,privatedisabilityamount string ,enrollmentid string ,socsecretirement string ,socsecretirementamount string ,ssdi string ,ssdiamount string ,ssi string ,ssiamount string ,tanf string ,tanfamount string ,totalmonthlyincome string ,unemployment string ,unemploymentamount string ,vadisabilitynonservice string ,vadisabilitynonserviceamount string ,vadisabilityservice string ,vadisabilityserviceamount string ,workerscomp string ,workerscompamount string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:alimony,CF:alimonyamount,CF:childsupport,CF:childsupportamount,CF:earned,CF:earnedamount,CF:ga,CF:gaamount,CF:id,CF:incomefromanysource,CF:othersource,CF:othersourceamount,CF:othersourceidentify,CF:pension,CF:pensionamount,CF:privatedisability,CF:privatedisabilityamount,CF:enrollmentid,CF:socsecretirement,CF:socsecretirementamount,CF:ssdi,CF:ssdiamount,CF:ssi,CF:ssiamount,CF:tanf,CF:tanfamount,CF:totalmonthlyincome,CF:unemployment,CF:unemploymentamount,CF:vadisabilitynonservice,CF:vadisabilitynonserviceamount,CF:vadisabilityservice,CF:vadisabilityserviceamount,CF:workerscomp,CF:workerscompamount,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Incomeandsources");
CREATE EXTERNAL TABLE IF NOT EXISTS inventory(key string,id string ,availabilty string ,bedinventory string ,bedtype string ,hmisparticipatingbeds string ,householdtype string ,inventoryenddate timestamp ,inventorystartdate timestamp ,project_coc_id string ,unitinventory string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:availabilty,CF:bedinventory,CF:bedtype,CF:hmisparticipatingbeds,CF:householdtype,CF:inventoryenddate,CF:inventorystartdate,CF:project_coc_id,CF:unitinventory,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Inventory");
CREATE EXTERNAL TABLE IF NOT EXISTS lastgradecompleted(key string,lastgradecompleted string ,id string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:lastgradecompleted,CF:id,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Lastgradecompleted");
CREATE EXTERNAL TABLE IF NOT EXISTS lastpermanentaddress(key string,id string ,addressdataquality string ,lastpermanentcity string ,lastpermanentstate string ,lastpermanentstreet string ,lastpermanentzip string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:addressdataquality,CF:lastpermanentcity,CF:lastpermanentstate,CF:lastpermanentstreet,CF:lastpermanentzip,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Lastpermanentaddress");
CREATE EXTERNAL TABLE IF NOT EXISTS medicalassistance(key string,adap string ,hivaidsassistance string ,id string ,noadapreason string ,nohivaidsassistancereason string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:adap,CF:hivaidsassistance,CF:id,CF:noadapreason,CF:nohivaidsassistancereason,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Medicalassistance");
CREATE EXTERNAL TABLE IF NOT EXISTS noncashbenefits(key string,benefitsfromanysource string ,id string ,othersource string ,othersourceidentify string ,othertanf string ,enrollmentid string ,rentalassistanceongoing string ,rentalassistancetemp string ,snap string ,tanfchildcare string ,tanftransportation string ,wic string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:benefitsfromanysource,CF:id,CF:othersource,CF:othersourceidentify,CF:othertanf,CF:enrollmentid,CF:rentalassistanceongoing,CF:rentalassistancetemp,CF:snap,CF:tanfchildcare,CF:tanftransportation,CF:wic,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Noncashbenefits");
CREATE EXTERNAL TABLE IF NOT EXISTS organization(key string,organizationcommonname string ,id string ,organizationname string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:organizationcommonname,CF:id,CF:organizationname,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Organization");
CREATE EXTERNAL TABLE IF NOT EXISTS path_status(key string,id string ,date_of_status timestamp ,client_enrolled_in_path string ,reason_not_enrolled string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:date_of_status,CF:client_enrolled_in_path,CF:reason_not_enrolled,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Pathstatus");
CREATE EXTERNAL TABLE IF NOT EXISTS percent_ami(key string,id string ,percentage string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:percentage,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Percentami");
CREATE EXTERNAL TABLE IF NOT EXISTS project(key string,id string ,enrollmentid string ,continuumproject string ,organizationid string ,projectcommonname string ,projectname string ,projecttype string ,residentialaffiliation string ,targetpopulation string ,trackingmethod string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:continuumproject,CF:organizationid,CF:projectcommonname,CF:projectname,CF:projecttype,CF:residentialaffiliation,CF:targetpopulation,CF:trackingmethod,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Project");
CREATE EXTERNAL TABLE IF NOT EXISTS projectcoc(key string,id string ,coccode string ,projectid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:coccode,CF:projectid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Projectcoc");
CREATE EXTERNAL TABLE IF NOT EXISTS projectcompletionstatus(key string,earlyexitreason string ,exitid string ,projectcompletionstatus string ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:earlyexitreason,CF:exitid,CF:projectcompletionstatus,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Projectcompletionstatus");
CREATE EXTERNAL TABLE IF NOT EXISTS referralsource(key string,countoutreachreferralapproaches string ,enrollmentid string ,referralsource string ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:countoutreachreferralapproaches,CF:enrollmentid,CF:referralsource,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Referralsource");
CREATE EXTERNAL TABLE IF NOT EXISTS residentialmoveindate(key string,inpermanenthousing string ,enrollmentid string ,residentialmoveindate timestamp ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:inpermanenthousing,CF:enrollmentid,CF:residentialmoveindate,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Residentialmoveindate");
CREATE EXTERNAL TABLE IF NOT EXISTS rhybcp_status(key string,id string ,status_date timestamp ,fysb_youth string ,reason_no_services string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:status_date,CF:fysb_youth,CF:reason_no_services,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Rhybcpstatus");
CREATE EXTERNAL TABLE IF NOT EXISTS schoolstatus(key string,id string ,information_date timestamp ,school_status string ,enrollmentid string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:information_date,CF:school_status,CF:enrollmentid,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Schoolstatus");
CREATE EXTERNAL TABLE IF NOT EXISTS services(key string,dateprovided timestamp ,faamount string ,othertypeprovided string ,enrollmentid string ,recordtype string ,referraloutcome string ,id string ,subtypeprovided string ,typeprovided string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateprovided,CF:faamount,CF:othertypeprovided,CF:enrollmentid,CF:recordtype,CF:referraloutcome,CF:id,CF:subtypeprovided,CF:typeprovided,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Services");
CREATE EXTERNAL TABLE IF NOT EXISTS sexualorientation(key string,id string ,enrollmentid string ,sexualorientation string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:sexualorientation,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Sexualorientation");
CREATE EXTERNAL TABLE IF NOT EXISTS site(key string,id string ,address string ,city string ,geocode string ,principal_site string ,project_coc_id string ,state string ,zip string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:address,CF:city,CF:geocode,CF:principal_site,CF:project_coc_id,CF:state,CF:zip,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Site");
CREATE EXTERNAL TABLE IF NOT EXISTS veteran_info(key string,id string ,year_entrd_service string ,year_seperated string ,world_war_2 string ,korean_war string ,vietnam_war string ,desert_storm string ,afghanistan_oef string ,iraq_oif string ,iraq_ond string ,other_theater string ,military_branch string ,discharge_status string ,date_created timestamp ,date_updated timestamp ,user_id string ,client_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:year_entrd_service,CF:year_seperated,CF:world_war_2,CF:korean_war,CF:vietnam_war,CF:desert_storm,CF:afghanistan_oef,CF:iraq_oif,CF:iraq_ond,CF:other_theater,CF:military_branch,CF:discharge_status,CF:date_created,CF:date_updated,CF:user_id,CF:client_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "VeteranInfo");
CREATE EXTERNAL TABLE IF NOT EXISTS worsthousingsituation(key string,enrollmentid string ,worsthousingsituation string ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:enrollmentid,CF:worsthousingsituation,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Worsthousingsituation");
CREATE EXTERNAL TABLE IF NOT EXISTS youthcriticalissues(key string,abuseandneglectfam string ,abuseandneglectyouth string ,activemilitaryparent string ,alcoholdrugabusefam string ,alcoholdrugabuseyouth string ,healthissuesfam string ,healthissuesyouth string ,householddynamics string ,housingissuesfam string ,housingissuesyouth string ,incarceratedparent string ,incarceratedparentstatus string ,insufficientincome string ,mentaldisabilityfam string ,mentaldisabilityyouth string ,mentalhealthissuesfam string ,mentalhealthissuesyouth string ,physicaldisabilityfam string ,physicaldisabilityyouth string ,enrollmentid string ,schooleducationalissuesfam string ,schooleducationalissuesyouth string ,sexualorientationgenderidfam string ,sexualorientationgenderidyouth string ,unemploymentfam string ,unemploymentyouth string ,id string ,date_created timestamp ,date_updated timestamp ,user_id string ,export_id string, project_group_code string, deleted string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:abuseandneglectfam,CF:abuseandneglectyouth,CF:activemilitaryparent,CF:alcoholdrugabusefam,CF:alcoholdrugabuseyouth,CF:healthissuesfam,CF:healthissuesyouth,CF:householddynamics,CF:housingissuesfam,CF:housingissuesyouth,CF:incarceratedparent,CF:incarceratedparentstatus,CF:insufficientincome,CF:mentaldisabilityfam,CF:mentaldisabilityyouth,CF:mentalhealthissuesfam,CF:mentalhealthissuesyouth,CF:physicaldisabilityfam,CF:physicaldisabilityyouth,CF:enrollmentid,CF:schooleducationalissuesfam,CF:schooleducationalissuesyouth,CF:sexualorientationgenderidfam,CF:sexualorientationgenderidyouth,CF:unemploymentfam,CF:unemploymentyouth,CF:id,CF:date_created,CF:date_updated,CF:user_id,CF:export_id,CF:project_group_code,CF:deleted") TBLPROPERTIES ("hbase.table.name" = "Youthcriticalissues");

CREATE INDEX index_veteran_status ON TABLE client(veteran_status) AS 'COMPACT' WITH DEFERRED REBUILD;