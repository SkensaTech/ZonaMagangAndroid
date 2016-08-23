package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_sekolah
{
  private String nama;
  private String objectId;
  private String alamat;
  private java.util.Date updated;
  private java.util.Date created;
  private String ownerId;
  private String no_telp;
  private Integer id_sekolah;

  public tb_sekolah(){}

  public String getNama()
  {
    return nama;
  }

  public void setNama( String nama )
  {
    this.nama = nama;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getAlamat()
  {
    return alamat;
  }

  public void setAlamat( String alamat )
  {
    this.alamat = alamat;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getNo_telp()
  {
    return no_telp;
  }

  public void setNo_telp( String no_telp )
  {
    this.no_telp = no_telp;
  }

  public Integer getId_sekolah()
  {
    return id_sekolah;
  }

  public void setId_sekolah( Integer id_sekolah )
  {
    this.id_sekolah = id_sekolah;
  }

  public tb_sekolah(int id_sekolah, String nama){
    this.id_sekolah = id_sekolah;
    this.nama = nama;
  }
                                                    
  public tb_sekolah save()
  {
    return Backendless.Data.of( tb_sekolah.class ).save( this );
  }

  public Future<tb_sekolah> saveAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_sekolah> future = new Future<tb_sekolah>();
      Backendless.Data.of( tb_sekolah.class ).save( this, future );

      return future;
    }
  }

  public void saveAsync( AsyncCallback<tb_sekolah> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( tb_sekolah.class ).remove( this );
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
      Backendless.Data.of( tb_sekolah.class ).remove( this, future );

      return future;
    }
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).remove( this, callback );
  }

  public static tb_sekolah findById( String id )
  {
    return Backendless.Data.of( tb_sekolah.class ).findById( id );
  }

  public static Future<tb_sekolah> findByIdAsync( String id )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_sekolah> future = new Future<tb_sekolah>();
      Backendless.Data.of( tb_sekolah.class ).findById( id, future );

      return future;
    }
  }

  public static void findByIdAsync( String id, AsyncCallback<tb_sekolah> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).findById( id, callback );
  }

  public static tb_sekolah findFirst()
  {
    return Backendless.Data.of( tb_sekolah.class ).findFirst();
  }

  public static Future<tb_sekolah> findFirstAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_sekolah> future = new Future<tb_sekolah>();
      Backendless.Data.of( tb_sekolah.class ).findFirst( future );

      return future;
    }
  }

  public static void findFirstAsync( AsyncCallback<tb_sekolah> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).findFirst( callback );
  }

  public static tb_sekolah findLast()
  {
    return Backendless.Data.of( tb_sekolah.class ).findLast();
  }

  public static Future<tb_sekolah> findLastAsync()
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<tb_sekolah> future = new Future<tb_sekolah>();
      Backendless.Data.of( tb_sekolah.class ).findLast( future );

      return future;
    }
  }

  public static void findLastAsync( AsyncCallback<tb_sekolah> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).findLast( callback );
  }

  public static BackendlessCollection<tb_sekolah> find( BackendlessDataQuery query )
  {
    return Backendless.Data.of( tb_sekolah.class ).find( query );
  }

  public static Future<BackendlessCollection<tb_sekolah>> findAsync( BackendlessDataQuery query )
  {
    if( Backendless.isAndroid() )
    {
      throw new UnsupportedOperationException( "Using this method is restricted in Android" );
    }
    else
    {
      Future<BackendlessCollection<tb_sekolah>> future = new Future<BackendlessCollection<tb_sekolah>>();
      Backendless.Data.of( tb_sekolah.class ).find( query, future );

      return future;
    }
  }

  public static void findAsync( BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_sekolah>> callback )
  {
    Backendless.Data.of( tb_sekolah.class ).find( query, callback );
  }
}