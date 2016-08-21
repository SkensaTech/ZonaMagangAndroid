package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_magang
{
  private String kode;
  private String objectId;
  private java.util.Date updated;
  private Integer id_siswa;
  private java.util.Date akhir_magang;
  private java.util.Date awal_magang;
  private java.util.Date created;
  private String id_magang;
  private Integer id_industri;
  private String ownerId;
  public String getKode()
  {
    return kode;
  }

  public void setKode( String kode )
  {
    this.kode = kode;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public Integer getId_siswa()
  {
    return id_siswa;
  }

  public void setId_siswa( Integer id_siswa )
  {
    this.id_siswa = id_siswa;
  }

  public java.util.Date getAkhir_magang()
  {
    return akhir_magang;
  }

  public void setAkhir_magang( java.util.Date akhir_magang )
  {
    this.akhir_magang = akhir_magang;
  }

  public java.util.Date getAwal_magang()
  {
    return awal_magang;
  }

  public void setAwal_magang( java.util.Date awal_magang )
  {
    this.awal_magang = awal_magang;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getId_magang()
  {
    return id_magang;
  }

  public void setId_magang( String id_magang )
  {
    this.id_magang = id_magang;
  }

  public Integer getId_industri()
  {
    return id_industri;
  }

  public void setId_industri( Integer id_industri )
  {
    this.id_industri = id_industri;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

                                                    
  public tb_magang save()
  {
    return Backendless.Data.of( tb_magang.class ).save( this );
  }

  public Future<tb_magang> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_magang> future = new Future<tb_magang>();
      Backendless.Data.of( tb_magang.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_magang> callback )
  {
    Backendless.Data.of( tb_magang.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_magang.class ).remove( this );
  }

  public Future<Long> removeAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<Long> future = new Future<Long>();
      Backendless.Data.of( tb_magang.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_magang.class ).remove( this, callback );
  }

  public static tb_magang findById( String id )
  {
    return Backendless.Data.of( tb_magang.class ).findById( id );
  }

  public static Future<tb_magang> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_magang> future = new Future<tb_magang>();
      Backendless.Data.of( tb_magang.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_magang> callback )
  {
    Backendless.Data.of( tb_magang.class ).findById( id, callback );
  }

  public static tb_magang findFirst()
  {
    return Backendless.Data.of( tb_magang.class ).findFirst();
  }

  public static Future<tb_magang> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_magang> future = new Future<tb_magang>();
      Backendless.Data.of( tb_magang.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_magang> callback )
  {
    Backendless.Data.of( tb_magang.class ).findFirst( callback );
  }

  public static tb_magang findLast()
  {
    return Backendless.Data.of( tb_magang.class ).findLast();
  }

  public static Future<tb_magang> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_magang> future = new Future<tb_magang>();
      Backendless.Data.of( tb_magang.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_magang> callback )
  {
    Backendless.Data.of( tb_magang.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_magang> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_magang.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_magang>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_magang>> future = new Future<BackendlessCollection<tb_magang>>();
      Backendless.Data.of( tb_magang.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_magang>> callback )
  {
    Backendless.Data.of( tb_magang.class ).find( query, callback );
  }
}